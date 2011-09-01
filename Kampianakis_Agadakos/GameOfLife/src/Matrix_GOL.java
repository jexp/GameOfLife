public class Matrix_GOL {
	final static int  MAX_DIM=5;
	Cell_GOL[][] board= new Cell_GOL[MAX_DIM][MAX_DIM];



	public void init(){

		for(int i = 0 ; i < MAX_DIM ; i++){
			for(int j = 0 ; j < MAX_DIM ; j++){

				if(Math.random() >= 0.5){
					board[i][j] = new  Cell_GOL(true) ;

				}else{
					board[i][j] = new Cell_GOL(false);
				}


			}
		}

	}

	public void check(){
		for(int i =0 ;i<MAX_DIM;i++){
			for(int j=0;j<MAX_DIM;j++){
				
				Cell_GOL curr_cell= board[i][j];
				
				int minX=i-1<0?0:i-1;
				int minY=j-1<0?0:j-1;
				int maxX=i+1>MAX_DIM?MAX_DIM:i+1;
				int maxY=j+1>MAX_DIM?MAX_DIM:j+1;

				int num_neighbors = curr_cell.isCurr_state()?-1:0;
				
				for(int k = minX ; k < maxX ; k++){
					for(int z = minY ; z < maxY ; z++){
						Cell_GOL neighb = board[k][z];
						if(neighb.isCurr_state()){
							num_neighbors++;
						}

					}

				}
				
				//evaluation
				if(curr_cell.isCurr_state() && ( num_neighbors == 2 || num_neighbors == 3) ){
					board[i][j].survive();
				}else if( (!curr_cell.isCurr_state()) && (num_neighbors == 3) ){
					board[i][j].born();
				}else{
					board[i][j].die();
				}
				
			}
		}




	}


	
	public void apply_all(){
		
		for(int i =0 ;i<MAX_DIM;i++){
			for(int j=0;j<MAX_DIM;j++){

				board[i][j].apply();
				
			}
		}
	}


	@Override
	public String toString(){
		StringBuilder mapBuilder= new StringBuilder();
		for(int i =0 ;i<MAX_DIM;i++){
			for(int j=0;j<MAX_DIM;j++){
				if(board[j][i].isCurr_state()){
					mapBuilder.append("*");
				}else{
					mapBuilder.append(" ");
				}
				mapBuilder.append("|");

			}
			mapBuilder.append("\n");
		}


		return mapBuilder.toString();

	}

}
