package eu3;
//Kastar Exception för en otillåten plats på planen
class NotValidFieldException extends Exception {
	NotValidFieldException(String s) {
		super(s);
	}
}
