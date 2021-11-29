package Lukuvinkkikirjasto;

public class Tip implements TipI {

    private final String header;
    private final String creator;
    private final String url;
    private final String type;
    private final String descpription;
    private final String tags;
    private final String comment;
    private final String courses;

    public Tip(String header, String description, String creator, String url, String type, String tags, String comment,
               String courses) {
        this.header = header;
        this.creator = creator;
        this.url = url;
        this.type = type;
        this.descpription = description;
        this.tags = tags;
        this.comment = tags;
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Tip{" +
                "header='" + header + '\'' +
                ", creator='" + creator + '\'' +
                ", url='" + url + '\'' +
                ", type='" + type + '\'' +
                ", descpription='" + descpription + '\'' +
                ", tags='" + tags + '\'' +
                ", comment='" + comment + '\'' +
                ", courses='" + courses + '\'' +
                '}';
    }
}