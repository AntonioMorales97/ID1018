package eu3;

public class ChessboardTest {
	public static void main (String [] args){
		java.util.Random rand = new java.util.Random();
		
		Chessboard chessboard = new Chessboard();
		Chessboard.Chesspiece[]pieces = new Chessboard.Chesspiece[6]; //outerclass.innerclass
		pieces[0]=chessboard.new Pawn ('w','P'); //outerobject.new innerclass
		pieces[1]=chessboard.new Bishop ('b','B');
		pieces[2]=chessboard.new Rook ('b','R');
		pieces[3]=chessboard.new Knight ('w','N');
		pieces[4]=chessboard.new Queen ('w','Q');
		pieces[5]=chessboard.new King ('b','K');
	
		for (int i = 0; i < pieces.length; i++){
		try{
		pieces[i].moveTo((char)(97+rand.nextInt(8)), (byte)(rand.nextInt(8)+1));
		} catch (NotValidFieldException err){
			System.err.println("ERROR: Not a valid field. "+err.getMessage());
		}
		pieces[i].markReachableFields();
		System.out.println(chessboard);
		pieces[i].unmarkReachableFields();
		pieces[i].moveOut();
		
		}
	}
}
