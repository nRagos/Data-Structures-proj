package src;

public class GenericsSearch {

    private String t;
    private String [] s;
    private String[] dif;
    private int i;

    /**
     *
     * @param t term you want to search for
     * @param s array of strings to search in
     */
    public GenericsSearch(String t,String[] s){
        this.t=t;
        this.s=s;
    }

    /**
     * function to search for a line based on given term
     * @return returns line that has been searched for
     */
    public String search(){
        for ( i=0; i<s.length;i++){
            if (s[i]!=null) {
                dif = s[i].split("\t");
                if (dif[0].equals(t)) {

                    return s[i].toString();
                }
            }
        }
        return "error 404 term not found";
    }

    /**
     *
     * @return returns the index value in array of the term
     */
    public int getIndex(){
        return i;
    }

    /**
     *
     * @return returns to confidence value of searched term
     */
    public String getC(){
        return dif[2];
    }

    /**
     *full search method for arrays
     * @param st given statement to search for
     * @return returns whole line based off given term and statement
     */
    public String fullSearch(String st){
        for ( i=0; i<s.length;i++){
            if (s[i]!=null) {
                dif = s[i].split("\t");
                if (dif[0].equals(t)) {
                    if (dif[1].equals(st)) {
                        return dif[2];
                    }
                }
            }
        }
        return null;
    }
    public void update(String[] up){
        s= up;
    }
}
