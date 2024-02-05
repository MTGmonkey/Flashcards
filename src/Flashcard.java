import java.util.ArrayList;

public class Flashcard {
    private ArrayList<String> clues;
    private ArrayList<String> answers;

    public Flashcard() {
        this(new ArrayList<String>(), new ArrayList<String>());
        clues.add("clue");
        answers.add("answer");
    }

    public Flashcard(String saveline) {

        clues = new ArrayList<String>();
        answers = new ArrayList<String>();

        if( saveline.split(";").length == 2) {
            String[] clus = saveline.split(";")[0].split(",");
            String[] ansrs = saveline.split(";")[1].split(",");
            for (int i = 0; i < clus.length; i++ )
                clues.add(clus[i].strip());
            for (int i = 0; i < ansrs.length; i++)
                answers.add(ansrs[i].strip());
        }

    }
    public Flashcard(ArrayList<String> clues, ArrayList<String> answers) {
        this.clues = clues;
        this.answers = answers;
    }

    public String getClue() { return clues.get(clues.size() - 1); }

    public ArrayList<String> getClues() { return clues; }

    public String getClue(int index) { return clues.get(index); }

    public void setClues(ArrayList<String> clues) {
        this.clues = clues;
    }

    public void addClue(String clue) {
        clues.add(clue);
    }

    public void removeClue() {
        clues.remove(clues.size() - 1);
        if (clues.size() < 1)
            clues.add("clue");
    }
    public ArrayList<String> getAnswers() {
        return answers;
    }

    public String getAnswer() {
        return answers.get(answers.size() - 1);
    }

    public String getAnswer(int index) {
        return answers.get(index);
    }

    public void setAnswers(ArrayList<String> answers) {
        this.answers = answers;
    }

    public void addAnswer(String answer) {
        answers.add(answer);
    }

    public void removeAnswer() {
        answers.remove(answers.size() - 1);
        if (answers.size() < 1)
            answers.add("answer");
    }

    public String createSaveline() {
        String saveline = "";

        for (String s : clues)
            saveline += s + ",";
        saveline = saveline.substring(0, saveline.length() - 1) + ";";
        for (String s : answers)
            saveline += s + ",";
        saveline = saveline.substring(0, saveline.length() - 1);

        return saveline;

    }

    public String toString() {
        String toRet = "[Flashcard ";
        toRet += createSaveline();
        return toRet + "]";
    }

}
