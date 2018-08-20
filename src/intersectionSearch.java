

import java.util.ArrayList;

public class intersectionSearch {				//ulekaigujaama otsimise klass

	metroLine[] lines;

	public intersectionSearch(metroLine[] lines2) {
		this.lines = lines2;
	}
	public ArrayList<String> intersections(){
		ArrayList<String> intersList = new ArrayList<>();
		for(int i=0;i<lines.length;i++) {
			for(int j=0;j<lines.length;j++) {
				if(!lines[i].equals(lines[j])) {
					for(String each1 : lines[i].getStations()) {
						for(String each2 : lines[j].getStations()) {
							if ((!intersList.contains(each1))&&each1.equals(each2)) {
								intersList.add(each1);
							}
						}
					}
				}
			}
		}
		return intersList;
	}


}