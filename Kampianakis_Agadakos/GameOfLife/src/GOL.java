import java.io.Console;


public class GOL implements Runnable{
	static Integer a=new Integer(2);
	public GOL(){


	};

	public static void main(String[] args) {

		GOL g = new GOL();

		g.run();


	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Matrix_GOL mat = new Matrix_GOL();
		mat.init();

		System.out.println(mat.toString());

		for(int i = 0 ;i < 10 ; i++){
			System.out.println("p : "+i+"\n\n");
			mat.check();			
			mat.apply_all();

			System.out.println(mat.toString());



		}





	}




}
