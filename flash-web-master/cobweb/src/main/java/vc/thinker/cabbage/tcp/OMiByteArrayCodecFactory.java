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
 * 欧米数据编码
 * @author james
 *
 */
public class OMiByteArrayCodecFactory implements ProtocolCodecFactory {
	private static final Logger log = LoggerFactory.getLogger(OMiByteArrayCodecFactory.class);

	 private ProtocolEncoder encoder;
	 private ProtocolDecoder decoder;

	 public OMiByteArrayCodecFactory() {
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

		 /*byte[] buf = new byte[in.limit()];
	      in.get(buf);
	      String msg = new String(buf).trim();
	      log.info("hex:["+Command.byteArrayToHex2(buf)+"]->decode接收命令:"+msg + "->msg.endsWith(\"#\")"+msg.endsWith("#"));
	      if(msg.endsWith("#")){
	    	  log.info("decode接收命令2:"+msg);
	        out.write(buf);
	        return true;
	      }else{
	        return false;
	      }*/
		  in.mark();
	      byte[] buf = in.array();
	      int len = in.limit();
	          String msg = new String(buf).trim();
	          log.info("decode接收命令:"+msg + "->msg.endsWith(\"#\")"+msg.endsWith("#"));
	 	     
	          if(msg.endsWith("#")){

	        	  log.info("decode接收命令2:"+msg);
	                in.reset();
	                byte[] packArr = new byte[len];
	                in.get(packArr, 0 , len);

	             
	                out.write(packArr);
	              

	                if(in.remaining() > 0){
	                    return false;
	                }

	            return true;
	          }else{
	                in.reset();

	            return false;
	          }
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