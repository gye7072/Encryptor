public class Encryptor
{
    /** A two-dimensional array of single-character strings, instantiated in the constructor */
    private String[][] letterBlock;

    /** The number of rows of letterBlock, set by the constructor */
    private int numRows;

    /** The number of columns of letterBlock, set by the constructor */
    private int numCols;

    /** Constructor*/
    public Encryptor(int r, int c)
    {
        letterBlock = new String[r][c];
        numRows = r;
        numCols = c;
    }

    public String[][] getLetterBlock()
    {
        return letterBlock;
    }

    /** Places a string into letterBlock in row-major order.
     *
     *   @param str  the string to be processed
     *
     *   Postcondition:
     *     if str.length() < numRows * numCols, "A" in each unfilled cell
     *     if str.length() > numRows * numCols, trailing characters are ignored
     */
    public void fillBlock(String str)
    {
        int count = 0;
        for(int i = 0; i < letterBlock.length; i++){
            for(int j = 0; j < letterBlock[0].length; j++){
                if(count < str.length()){
                    letterBlock[i][j] = str.substring(count, count+1);
                    count++;
                } else{
                    letterBlock[i][j] = "A";
                }
            }
        }
    }

    /** Extracts encrypted string from letterBlock in column-major order.
     *
     *   Precondition: letterBlock has been filled
     *
     *   @return the encrypted string from letterBlock
     */
    public String encryptBlock()
    {
        String str = "";
        for(int j = 0; j < letterBlock[0].length; j++){
            for(int i = 0; i < letterBlock.length; i++){
                str += letterBlock[i][j];
            }
        }
        return str;
    }

    /** Encrypts a message.
     *
     *  @param message the string to be encrypted
     *
     *  @return the encrypted message; if message is the empty string, returns the empty string
     */
    public String encryptMessage(String message)
    {
      String str = "";
      double num = Math.ceil( (double) (message.length())/(numRows * numCols));
      for(int i = 0; i < num; i++){
          if(numRows * numCols + numRows * numCols * i > message.length()){
              fillBlock(message.substring(numRows * numCols * i, message.length()));
          }
          else {
              fillBlock(message.substring(numRows * numCols * i, numRows * numCols + numRows * numCols * i));
          }
          str += encryptBlock();
      }
      return str;
    }

    /**  Decrypts an encrypted message. All filler 'A's that may have been
     *   added during encryption will be removed, so this assumes that the
     *   original message (BEFORE it was encrypted) did NOT end in a capital A!
     *
     *   NOTE! When you are decrypting an encrypted message,
     *         be sure that you have initialized your Encryptor object
     *         with the same row/column used to encrypted the message! (i.e.
     *         the “encryption key” that is necessary for successful decryption)
     *         This is outlined in the precondition below.
     *
     *   Precondition: the Encryptor object being used for decryption has been
     *                 initialized with the same number of rows and columns
     *                 as was used for the Encryptor object used for encryption.
     *
     *   @param encryptedMessage  the encrypted message to decrypt
     *
     *   @return  the decrypted, original message (which had been encrypted)
     *
     *   TIP: You are encouraged to create other helper methods as you see fit
     *        (e.g. a method to decrypt each section of the decrypted message,
     *         similar to how encryptBlock was used)
     */
    public String decryptMessage(String encryptedMessage) {
        int count = 0;
        String str = "";
        double num = Math.ceil( (double) (encryptedMessage.length())/(numRows * numCols));
        for(int l = 0; l < num; l++) {
            for (int j = 0; j < numCols; j++) {
                for (int i = 0; i < numRows; i++) {
                    letterBlock[i][j] = encryptedMessage.substring(count, count + 1);
                    count++;
                }
            }

            for (int i2 = 0; i2 < numRows; i2++) {
                for (int j2 = 0; j2 < numCols; j2++) {
                    str += letterBlock[i2][j2];
                }
            }
        }
        while(str.charAt(str.length()-1) == ('A')){
            str = str.substring(0,str.length()-1);
        }

        return str;
    }
}
