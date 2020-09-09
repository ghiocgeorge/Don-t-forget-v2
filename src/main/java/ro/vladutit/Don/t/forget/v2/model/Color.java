package ro.vladutit.Don.t.forget.v2.model;

public enum Color {
    Orange("color: orange;"),
    Green("color: green;"),
    Red("color: red;"),
    Yellow("color: yellow;"),
    Black("color: black;"),
    Grey("color: gray;"),
    LightGray("color: LightGray;"),
    Blue("color: blue;"),
    Purple("color: purple;"),
    Tomato("color: tomato;"),
    Violet("color: violet;"),
    DodgerBlue("color: DodgerBlue;"),
    MediumSeaGreen("color: MediumSeaGreen;"),
    SlateBlue("color: SlateBlue;");

    private final String colorCode;

    Color(String colorCode) {
        this.colorCode = colorCode;
    }

    public String getColorCode() {
        return colorCode;
    }
}
