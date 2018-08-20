public class metroLine {							//eriklass igale metrooliinile(voimalus edasi arenemisele - failist lugemisele ja arvutamisele suvalise metroosusteemiga)
	private String name;
	private String[] stations;
	public metroLine(String name, String[] stations) {
		this.name = name;
		this.stations = stations;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String[] getStations() {
		return stations;
	}
	public void setStations(String[] stations) {
		this.stations = stations;
	}
	

}