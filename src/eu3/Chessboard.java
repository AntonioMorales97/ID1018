package eu3;

public class Chessboard {
	//Fr�ga 2.
	//static-nested class: Logiskt: Anv�nds och �r bara anv�ndbart f�r en enda klass, Chessboard.
	//Inkapsling: Field kan vara "g�md" och inte anv�ndas i yttre v�rlden.
	//L�s- och h�llbar kod: L�gger kod som anv�nds tillsammans n�ra varandra
	public static class Field {
		private char row;
		private byte column;
		private Chesspiece piece = null;
		private boolean marked = false;
		
		// row f�r f�ltet, column f�r f�ltet
		public Field(char row, byte column) {
			this.row = row;
			this.column = column;
		}

		// L�gger till en spelpj�s
		public void put(Chesspiece piece) {
			this.piece = piece;
		}

		// Tar bort spelpj�sen och returnerar att spelpj�sen
		// ej l�ngre finns kvar
		public Chesspiece take() {
			this.piece = null;
			return null;
		}

		// Markerar
		public void mark() {
			this.marked = true;
		}

		//Avmarkerar
		public void unmark() {
			this.marked = false;
		}

		//Skriver ut om det �r markerad eller inte
		//och om det finns en spelpj�s s� skrivs den ut ist�llet
		//return str�ng eller spelpj�s
		public String toString() {
			String s = (marked) ? "xx" : "--";
			return (piece == null) ? s : piece.toString();
		}
	}

	public static final int NUMBER_OF_ROWS = 8;
	public static final int NUMBER_OF_COLUMNS = 8;

	public static final int FIRST_ROW = 'a';
	public static final int FIRST_COLUMN = 1;

	private Field[][] fields;

	// Chessboard skapar en spelplan med 8 antal rader och
	// kolumner d�r kolumner �r heltal och rader
	// �r bokst�ver, b�rjar p� a
	public Chessboard() {
		fields = new Field[NUMBER_OF_ROWS][NUMBER_OF_COLUMNS]; // [0-7][0-7]
		char row = 0;
		byte column = 0;
		for (int r = 0; r < NUMBER_OF_ROWS; r++) { 
			row = (char) (FIRST_ROW + r); //a,b,c,...
			column = FIRST_COLUMN;
			for (int c = 0; c < NUMBER_OF_COLUMNS; c++) {
				fields[r][c] = new Field(row, column); 
				column++;
			}
		}
	}
	//Skriver ut Chessboard
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("    1  2  3  4  5  6  7  8\n");
		for (int i = 0; i < 8; i++) {
			s.append((char) (i + FIRST_ROW) + " ");// a+0=a,a+1 = b..char numbers
			for (int j = 0; j < 8; j++) {
				s.append(" " + fields[i][j]);// l�gger till fields f�r resp rad
				if (j == 7) {
					s.append("\n"); // sista kolumn,l�gg till ny rad, s� n�sta
									// rad b�rjar p� en ny rad
				}
			}
		}
		return s.toString();
	}

	// Kollar om den angivna platsen finns p� spelf�ltet
	//returnerar true eller false
	public boolean isValidField(char row, byte column) {
		return (row - FIRST_ROW < NUMBER_OF_ROWS && row - FIRST_ROW >= 0 && column - FIRST_COLUMN < NUMBER_OF_COLUMNS
				&& column - FIRST_COLUMN >= 0);
	}
	// Spelpj�s-objekt. color - f�rg f�r pj�sen. name - namn p� pj�sen
	// row - rad f�r pj�sen. column - kolumn f�r pj�sen.
	//Beh�ver anv�nda metoder m.fl. fr�n den yttre klassen, d�rf�r inte static.
	public abstract class Chesspiece {
		private char color; // w - white, b - black

		private char name; // K - King, Q - Queen, R - Rook, B - Bishop, N -
							// Knight, // P � Pawn

		protected char row = 0;
		protected byte column = -1;

		//Konstruktor
		protected Chesspiece(char color, char name) {
			this.color = color;
			this.name = name;
		}

		//Skriver pj�sens f�rg och namn i en str�ng
		public String toString() {
			return "" + color + name;
		}

		// Kollar om pj�sen kan finnas p� spelplanen 
		public boolean isOnBoard() {
			return Chessboard.this.isValidField(row, column);
		}

		// Flyttar pj�sen till den platsen och en exception sker
		// om pj�sen �r utanf�r planen
		public void moveTo(char row, byte column) throws NotValidFieldException {
			if (!Chessboard.this.isValidField(row, column))
				throw new NotValidFieldException("bad field: " + row + column);

			this.row = row;
			this.column = column;

			int r = row - FIRST_ROW;
			int c = column - FIRST_COLUMN;
			Chessboard.this.fields[r][c].put(this); //s�tter denna pj�s-objekt i denna plats
		}

		// Tar bort spelpj�sen fr�n planen
		public void moveOut() {
			if (isOnBoard())
				Chessboard.this.fields[this.row - FIRST_ROW][this.column-FIRST_COLUMN].take();
		}
		
		//Markerar f�lt f�r pj�serna
		public abstract void markReachableFields();
		
		//Avmakerar f�lt f�r pj�serna
		public abstract void unmarkReachableFields();

		protected void markDiagonals() {
			for (int i = 1; i < NUMBER_OF_ROWS; i++) {
				// nedH (raderna++,kolumn++)
				if (Chessboard.this.isValidField((char) (this.row + i), (byte) (this.column + i))) {
					Chessboard.this.fields[this.row + i - FIRST_ROW][this.column + i - FIRST_COLUMN].mark();
				}
				
				if (Chessboard.this.isValidField((char) (this.row + i), (byte) (this.column - i))) {
					Chessboard.this.fields[this.row + i - FIRST_ROW][this.column - i - FIRST_COLUMN].mark();
				}
				
				if (Chessboard.this.isValidField((char) (this.row - i), (byte) (this.column + i))) {
					Chessboard.this.fields[this.row - i - FIRST_ROW][this.column + i - FIRST_COLUMN].mark();
				}
				
				if (Chessboard.this.isValidField((char) (this.row - i), (byte) (this.column - i))) {
					Chessboard.this.fields[this.row - i - FIRST_ROW][this.column - i - FIRST_COLUMN].mark();
				}
			}

		}

		protected void unmarkDiagonals() {
			for (int i = 1; i < NUMBER_OF_ROWS; i++) {

				if (Chessboard.this.isValidField((char) (this.row + i), (byte) (this.column + i))) {
					Chessboard.this.fields[this.row + i - FIRST_ROW][this.column + i - FIRST_COLUMN].unmark();
				}

				if (Chessboard.this.isValidField((char) (this.row + i), (byte) (this.column - i))) {
					Chessboard.this.fields[this.row + i - FIRST_ROW][this.column - i - FIRST_COLUMN].unmark();
				}

				if (Chessboard.this.isValidField((char) (this.row - i), (byte) (this.column + i))) {
					Chessboard.this.fields[this.row - i - FIRST_ROW][this.column + i - FIRST_COLUMN].unmark();
				}

				if (Chessboard.this.isValidField((char) (this.row - i), (byte) (this.column - i))) {
					Chessboard.this.fields[this.row - i - FIRST_ROW][this.column - i - FIRST_COLUMN].unmark();
				}
			}
		}
		//markerar de raka linjerna fr�n d�r pj�sen befinner sig p�
		protected void markStraights() {
			for (int i = 1; i < NUMBER_OF_ROWS; i++) { 

				if (Chessboard.this.isValidField((char) (this.row + i), this.column)) {
					Chessboard.this.fields[this.row + i - FIRST_ROW][this.column - FIRST_COLUMN].mark();
				}

				if (Chessboard.this.isValidField((char) (this.row - i), this.column)) {
					Chessboard.this.fields[this.row - i - FIRST_ROW][this.column - FIRST_COLUMN].mark();
				}

				if (Chessboard.this.isValidField((char) (this.row), (byte) (this.column + i))) {
					Chessboard.this.fields[this.row - FIRST_ROW][this.column + i - FIRST_COLUMN].mark();
				}

				if (Chessboard.this.isValidField((char) (this.row), (byte) (this.column - i))) {
					Chessboard.this.fields[this.row - FIRST_ROW][this.column - i - FIRST_COLUMN].mark();
				}
			}
		}

		protected void unmarkStraights() {
			for (int i = 1; i < NUMBER_OF_ROWS; i++) {

				if (Chessboard.this.isValidField((char) (this.row + i), this.column)) {
					Chessboard.this.fields[this.row + i - FIRST_ROW][this.column - FIRST_COLUMN].unmark();
				}

				if (Chessboard.this.isValidField((char) (this.row - i), this.column)) {
					Chessboard.this.fields[this.row - i - FIRST_ROW][this.column - FIRST_COLUMN].unmark();
				}

				if (Chessboard.this.isValidField((char) (this.row), (byte) (this.column + i))) {
					Chessboard.this.fields[this.row - FIRST_ROW][this.column + i - FIRST_COLUMN].unmark();
				}

				if (Chessboard.this.isValidField((char) (this.row), (byte) (this.column - i))) {
					Chessboard.this.fields[this.row - FIRST_ROW][this.column - i - FIRST_COLUMN].unmark();
				}
			}
		}
	}
	//child till parent-class Chesspiece
	public class Pawn extends Chesspiece {
		public Pawn(char color, char name) {
			super(color, name);
		}

		public void markReachableFields() {
			byte col = (byte) (column + 1);
			if (Chessboard.this.isValidField(row, col)) {
				int r = row - FIRST_ROW;
				int c = col - FIRST_COLUMN;
				Chessboard.this.fields[r][c].mark();
			}
		}

		public void unmarkReachableFields() {
			byte col = (byte) (column + 1);
			if (Chessboard.this.isValidField(row, col)) {
				int r = row - FIRST_ROW;
				int c = col - FIRST_COLUMN;
				Chessboard.this.fields[r][c].unmark();
			}
		}
	}

	public class Bishop extends Chesspiece {
		public Bishop(char color, char name) {
			super(color, name);
		}

		public void markReachableFields() {
			this.markDiagonals();
		}

		public void unmarkReachableFields() {
			this.unmarkDiagonals();
		}
	}

	public class Rook extends Chesspiece {

		public Rook(char color, char name) {
			super(color, name);
		}

		public void markReachableFields() {
			this.markStraights();
		}

		public void unmarkReachableFields() {
			this.unmarkStraights();
		}
	}

	public class Knight extends Chesspiece {

		private int[][] moves = { { 1, 2 }, { 1, -2 }, { 2, 1 }, { 2, -1 }, { -1, 2 }, { -1, -2 }, { -2, 1 },
				{ -2, -1 } };

		public Knight(char color, char name) {
			super(color, name);
		}

		public void markReachableFields() {
			for (int i = 0; i < moves.length; i++) {
				if (Chessboard.this.isValidField((char) (this.row + moves[i][0]), (byte) (this.column + moves[i][1]))) {
					Chessboard.this.fields[this.row + moves[i][0] - FIRST_ROW][this.column + moves[i][1] - FIRST_COLUMN]
							.mark();
				}
			}
		}

		public void unmarkReachableFields() {
			for (int i = 0; i < moves.length; i++) {
				if (Chessboard.this.isValidField((char) (this.row + moves[i][0]), (byte) (this.column + moves[i][1]))) {
					Chessboard.this.fields[this.row + moves[i][0] - FIRST_ROW][this.column + moves[i][1] - FIRST_COLUMN]
							.unmark();
				}
			}
		}
	}

	public class Queen extends Chesspiece {
		
		public Queen (char color, char name){
			super(color,name);
		}
		
		public void markReachableFields(){
			this.markDiagonals();
			this.markStraights();
		}
		
		public void unmarkReachableFields(){
			this.unmarkDiagonals();
			this.unmarkStraights();
		}
		
	}
  
	 public class King extends Chesspiece {
		 private int [][] moves = {{0,1},{0,-1},{1,0},{-1,0},{1,1},{1,-1},
				 										  {-1,1},{-1,-1}}; //{rad,kolumn} som matris
		 public King (char color, char name){
			 super(color,name);
		 }
		 
		 public void markReachableFields(){
			 for (int i = 0; i < moves.length;i++){
				 if(Chessboard.this.isValidField((char)(this.row + moves[i][0]), (byte)(this.column + moves[i][1]))){
					 Chessboard.this.fields[this.row+moves[i][0]-FIRST_ROW][this.column+moves[i][1]-FIRST_COLUMN].mark();
				 }
			 }
		 }
		 
		 public void unmarkReachableFields(){
			 for (int i = 0; i < moves.length;i++){
				 if(Chessboard.this.isValidField((char)(this.row + moves[i][0]), (byte)(this.column + moves[i][1]))){
					 Chessboard.this.fields[this.row+moves[i][0]-FIRST_ROW][this.column+moves[i][1]-FIRST_COLUMN].unmark();
				 }
			 }
		 }
	 }
	 
}
