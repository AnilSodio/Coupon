import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;

public class AkkaTest {

	   public static void main(String[] args) {
		    ActorSystem system = ActorSystem.create("Hello");
		    ActorRef a = system.actorOf(Props.create(HelloWorld.class), "helloWorld");
		    System.out.println(a.path());
	    }

	   public static class HelloWorld extends UntypedActor {

		   @Override
		   public void preStart() {
		     // create the greeter actor
		     final ActorRef greeter = getContext().actorOf(Props.create(Greeter.class), "greeter");
		     // tell it to perform the greeting
		     greeter.tell(Greeter.Msg.GREET, getSelf());
		   }

		   @Override
		   public void onReceive(Object msg) {
		     if (msg == Greeter.Msg.DONE) {
		       // when the greeter is done, stop this actor and with it the application
		       getContext().stop(getSelf());
		     } else
		       unhandled(msg);
		   }
		 }
	   
	   public static class Greeter extends UntypedActor {

		   public static enum Msg {
		     GREET, DONE;
		   }

		   @Override
		   public void onReceive(Object msg) throws InterruptedException {
		     if (msg == Msg.GREET) {
		       System.out.println("Hello World!");
		       Thread.sleep(1000);
		       getSender().tell(Msg.DONE, getSelf());
		     } else
		       unhandled(msg);
		   }

		 }
}
