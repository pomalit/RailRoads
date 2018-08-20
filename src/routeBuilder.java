
import java.util.ArrayList;

public class routeBuilder {

	private metroLine[] lines;
	private ArrayList<String> nodes;
	private ArrayList<int[]> Matriks = new ArrayList<>();
	private ArrayList<String> visited = new ArrayList<>();
	protected ArrayList<String> tee = new ArrayList<>();				//-mittekasutatud funktsioonid(work in progress)
	private ArrayList<String> checked = new ArrayList<>();
	private int stLine;
	private int stStation;
	private int taLine;
	private int taStation;

/*
	public routeBuilder(ArrayList<metroLine> lines, int stLine, int stStation, int taLine, int taStation) {
		super();
		this.lines = lines;
		this.stLine = stLine;
		this.stStation = stStation;
		this.taLine = taLine;
		this.taStation = taStation;
	}
*/	
	
	public routeBuilder(metroLine[] lines, int stLine, int stStation, int taLine, int taStation) {
		super();
		this.lines = lines;
		this.stLine = stLine;
		this.stStation = stStation;
		this.taLine = taLine;
		this.taStation = taStation;
	}
	
	//pohimotte on jikstra algorutm luhedama tee arvestamiseks
	public int MatriksiKoostamine(){
		nodes = new intersectionSearch(lines).intersections(); //otsime ulekaigujaamad
		for (metroLine each : lines) {
			Matriks.add(new int[each.getStations().length]);
		}
		for (int i=0;i<Matriks.size();i++) {
			for (int el=0;el<Matriks.get(i).length;el++) {
				Matriks.get(i)[el] = Integer.MAX_VALUE / 2;
			}
		}
		// get start positions
		Matriks.get(stLine)[stStation] = 0; 					//koosasime graafi ja panime algjaamale vaartuss 0(kaugust aljaamast algjaamani = 0) 
		while (Matriks.get(taLine)[taStation] == Integer.MAX_VALUE / 2) {				//teeme tsukli kuni loppjaamani kaugus on teatud
			int minIndexStation = -1;
			int minIndexLine = -1;
			int minValue = Integer.MAX_VALUE / 2;

			for (int i=0;i<Matriks.size();i++) {
				for (int el=0;el<Matriks.get(i).length;el++) {
					if(Matriks.get(i)[el]<minValue && !(visited.contains(lines[i].getStations()[el]))) {				//vaatleme jaamasid mis ei ole veel vaadatud ja milleni kaugus on maaratud juba
						minValue = Matriks.get(i)[el];																		//esimesel kaigul meil niisugune on ainult uks - algjaam, siis lisavad temale lahenemad, jne
						minIndexStation = el;
						minIndexLine = i;
					}
				}
			}
			//======================================
			if((minValue !=Integer.MAX_VALUE / 2) && (minIndexStation > 0) && (minIndexStation < lines[minIndexLine].getStations().length-1)) {			//siin maarame kaugus lahenematele jaamadele
				if ( minValue + 1< Matriks.get(minIndexLine)[minIndexStation+1]) {
					Matriks.get(minIndexLine)[minIndexStation+1] = minValue + 1;
				}
				if (minValue + 1< Matriks.get(minIndexLine)[minIndexStation-1]) {
					Matriks.get(minIndexLine)[minIndexStation-1] = minValue + 1;
				}
			}
			//=====================================================
			if(minValue !=Integer.MAX_VALUE / 2){
				if (nodes.contains(lines[minIndexLine].getStations()[minIndexStation])) {
					for(int j = 0;j<Matriks.size();j++) {
						for(int obj = 0; obj<Matriks.get(j).length;obj++) {

							if(lines[j].getStations()[obj].equals(lines[minIndexLine].getStations()[minIndexStation])){
								if(minValue < Matriks.get(j)[obj]) {							//kui see on ulekaigu jaam, paneme teiste liinidele sama jaamale sama kaugusevaartus ja arvetame lahenemaid jaamasid
									Matriks.get(j)[obj] = minValue;
									if (obj <lines[minIndexLine].getStations().length-1) {
										if (Matriks.get(j)[obj+1]>minValue+1) {
											Matriks.get(j)[obj+1] = minValue+1;
										}

									}
									if (obj > 0) {
										if (Matriks.get(j)[obj-1]>minValue+1) {
											Matriks.get(j)[obj-1] = minValue+1;
										}
									}

								}else {
									if (obj <lines[minIndexLine].getStations().length-1) {
										if (Matriks.get(j)[obj+1]> Matriks.get(j)[obj]+1) {
											Matriks.get(j)[obj+1] = Matriks.get(j)[obj]+1;
										}

									}
									if (obj > 0) {
										if (Matriks.get(j)[obj-1]>Matriks.get(j)[obj]+1) {
											Matriks.get(j)[obj-1] = Matriks.get(j)[obj]+1;
										}
									}


								}
							}
						}
					}
				}
				visited.add(lines[minIndexLine].getStations()[minIndexStation]); //liisame vaadeldud jaama listi et  tema ei vaataks veel kord
			}
			//=================================
			if(minValue !=Integer.MAX_VALUE / 2){
				if (minIndexStation == 0) {
					if ( minValue + 1< Matriks.get(minIndexLine)[minIndexStation+1]) {
						Matriks.get(minIndexLine)[minIndexStation+1] = minValue + 1;
					}
					visited.add(lines[minIndexLine].getStations()[minIndexStation]);
				}
				if (minIndexStation == lines[minIndexLine].getStations().length-1) {
					if ( minValue + 1< Matriks.get(minIndexLine)[minIndexStation-1]) {
						Matriks.get(minIndexLine)[minIndexStation-1] = minValue + 1;
					}
					visited.add(lines[minIndexLine].getStations()[minIndexStation]);
				}
			}
		}

		int TeePikkus = Matriks.get(taLine)[taStation]; //paneme kaugust algjaamalt siia
		return TeePikkus;

	}

	public ArrayList<String> TeeLeidmine() {
		tee.add(lines[taLine].getStations()[taStation]);
		int pikkus = Matriks.get(taLine)[taStation]-1;
		System.out.println("STATIONS TO PASS:");
		while (!tee.contains(lines[stLine].getStations()[stStation])) {
			for (int i=0; i<Matriks.size();i++) {
				for (int el=0; el<Matriks.get(i).length;el++) {
					if(Matriks.get(i)[el]== pikkus && !(checked.contains(lines[i].getStations()[el]))) {
						if (el != 0 && el < Matriks.get(i).length-1) {
							if(Matriks.get(i)[el+1] == pikkus+1 || Matriks.get(i)[el-1] == pikkus+1) {
								if(tee.contains(lines[i].getStations()[el+1]) || tee.contains(lines[i].getStations()[el-1])) {
									tee.add(lines[i].getStations()[el]);
									pikkus -= 1;
								}
							}
						}
						if (el == 0) {
							if(Matriks.get(i)[el+1] == pikkus+1) {
								tee.add(lines[i].getStations()[el]);
								pikkus -= 1;
							}
						}
						if (el == Matriks.get(i).length-1) {
							if(Matriks.get(i)[el-1] == pikkus+1) {
								tee.add(lines[i].getStations()[el]);
								pikkus -= 1;
							}
						}
					}
				}
			}
			if (pikkus == 0) {
				tee.add(lines[stLine].getStations()[stStation]);
			}
		}
		for (int j=tee.size()-1;j>=0;j--){
			System.out.println(tee.get(j));
		}
		return tee;
	}
}