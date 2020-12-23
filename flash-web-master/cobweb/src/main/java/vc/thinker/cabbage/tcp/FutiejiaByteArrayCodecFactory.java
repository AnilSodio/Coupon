package vc.thinker.cabbage.tcp;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 伏特加
 * 
 * @author james
 *
 */
public class FutiejiaByteArrayCodecFactory implements ProtocolCodecFactory {
	private static final Logger log = LoggerFactory.getLogger(FutiejiaByteArrayCodecFactory.class);

	private ProtocolEncoder encoder;
	private ProtocolDecoder decoder;

	public FutiejiaByteArrayCodecFactory() {
		encoder = new ByteArrayEncoder();
		decoder = new ByteArrayDecoder();
	}

	public class ByteArrayEncoder extends ProtocolEncoderAdapter {

		@Override
		public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {
			byte[] bytes = (byte[]) message;
			IoBuffer buffer = IoBuffer.allocate(bytes.length);

			buffer.put(bytes);
			buffer.flip();
			out.write(buffer);
		}
	}

	public class ByteArrayDecoder extends CumulativeProtocolDecoder {

		@Override
		protected boolean doDecode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {

			in.mark();
			byte[] buf = in.array();
			int len = in.limit();

			byte[] packArr = new byte[len];
			in.get(packArr, 0, len);

			out.write(packArr);

			if (in.remaining() > 0) {
				return false;
			}

			return true;
		}
	}

	@Override
	public ProtocolDecoder getDecoder(IoSession arg0) throws Exception {
		return decoder;
	}

	@Override
	public ProtocolEncoder getEncoder(IoSession arg0) throws Exception {
		return encoder;
	}
}