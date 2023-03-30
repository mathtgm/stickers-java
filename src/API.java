public enum API {

    NASA("https://api.nasa.gov/planetary/apod?api_key=2ksEVqKZh7sjFuhcsMVe4GCjO8asobDLhREqXQoK&start_date=2023-01-01&end_date=2023-01-13", new ExtratorConteudoNASA()),
    IMDB("https://raw.githubusercontent.com/lukadev08/lukadev08.github.io/main/apidata/imdbtop250moviesdata.json", new ExtratorConteudoIMDB());

    private String url;
    private ExtratorCounteudo extrator;

    API(String url, ExtratorCounteudo extrator) {
        this.url = url;
        this.extrator = extrator;
    }

    public String getUrl() {
        return url;
    }

    public ExtratorCounteudo getExtrator() {
        return extrator;
    }
}
