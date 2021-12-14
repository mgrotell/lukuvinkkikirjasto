package lukuvinkkikirjasto.main;

import java.util.ArrayList;

public interface TipHandlerI {
    public void createTip(String header, String description, String creator, String url,
                          String type, String tags, String comment, String courses);

    public ArrayList<Tip> getAllTips();

    public void deleteTip(String header);

    public String searchTipsByTerm(String searchString, String searchField);

}