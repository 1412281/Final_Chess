package chess.duphong.singleplay;

import chess.model.AI;

public class AITakeMoveThread extends Thread{
	
	private chess.model.AI computer ; 
	
	
	
	public AITakeMoveThread(AI computer) {
		super();
		this.computer = computer;
	}



	@Override
	public void run() {
		
		super.run();
		try {
			computer.takeAMove();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
}
