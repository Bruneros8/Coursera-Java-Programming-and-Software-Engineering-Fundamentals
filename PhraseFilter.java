public class PhraseFilter implements Filter {
    private String where;
    private String phrase;

    public PhraseFilter(String loc, String ph) {
        where = loc;
        phrase = ph;
    }

    public boolean satisfies(QuakeEntry qe) {
        String title = qe.getInfo();
        if (where.equals("start")) {
            return title.startsWith(phrase);
        }
        else if (where.equals("end")) {
            return title.endsWith(phrase);
        }
        else {
            return title.contains(phrase);
        }
    }

    public String getName() {
        return "PhraseFilter";
    }
}