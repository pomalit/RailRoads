import java.util.Scanner;

public class Selector {

	static metroLine M1 = new metroLine("M1 - Red", new String[]{"Devyatkino","Grazhdansky Prospekt","Akademicheskaya","Politekhnicheskaya","Ploschad Muzhestva","Lesnaya","Vyborgskaya","Ploshchad Lenina","Chernyshevskaya","Ploshchad Vosstaniya/Mayakovskaya","Vladimirskaya/Dostoevskaya","Pushkinskaya/Zvenigorodskaya","Tekhnologichesky Institut","Baltiyskaya","Narvskaya","Kirovsky Zavod","Avtovo","Leninsky Prospekt","Prospekt Veteranov"});
	static metroLine M2 = new metroLine("M2 - Blue", new String[]{"Parnas","Prospect Prosvesheniya","Ozerki","Udelnaya","Pionerskaya","Chyornaya Rechka","Petrogradskaya","Gorkovskaya","Nevsky Prospekt/Gostiny Dvor","Sennaya Ploschad/Spasskaya/Sadovaya","Tekhnologichesky Institut","Frunzenskaya","Moskovskiye Vorota","Elektrosila","Park Pobedy","Moskovskaya","Zvyozdnaya","Kupchino"});
	static metroLine M3 = new metroLine("M3 - Green", new String[]{"Primorskaya","Vasileostrovskaya","Nevsky Prospekt/Gostiny Dvor","Ploshchad Vosstaniya/Mayakovskaya","Ploshchad Alexandra Nevskogo","Yelizarovskaya","Lomonosovskaya","Proletraskaya","Obukhovo","Rybatskoe"});
	static metroLine M4 = new metroLine("M4 - Orange", new String[]{"Sennaya Ploschad/Spasskaya/Sadovaya","Vladimirskaya/Dostoevskaya","Ligovsky Prospekt","Ploshchad Alexandra Nevskogo","Novocherkasskaya","Ladozhskaya","Prospekt Bolshevikov","Ulitsa Dybenko"});
	static metroLine M5 = new metroLine("M5 - Violet", new String[]{"Komendantskiy Prospekt","Staraya Derevnya","Krestovsky Ostrov","Chkalovskaya","Sportivnaya","Admiralteyskaya","Sennaya Ploschad/Spasskaya/Sadovaya","Pushkinskaya/Zvenigorodskaya","Obvodny Kanal","Volkovskaya","Bukharestskaya","Mezhdunarodnaya"});

	public static void main(String[] args) {

		metroLine[] lines = {M1,M2,M3,M4,M5};
		Scanner scan = new Scanner(System.in);
		System.out.println("Choose a start line from the list below(Type number for line): ");
		for(int i=0;i<lines.length;i++) {
			System.out.print(Integer.toString(i+1) + ") " + lines[i].getName() + "; " );
		}
		int stLine = scan.nextInt()-1;
		System.out.println("Choose a start station from the list below (Type number for line):");
		for(int i=0;i<lines[stLine].getStations().length;i++) {
			System.out.print(Integer.toString(i+1) + ") " + lines[stLine].getStations()[i] + "; " );
		}
		System.out.println();
		int stStation = scan.nextInt()-1;
		String stStationName = lines[stLine].getStations()[stStation];

		System.out.println("Choose a target line from the list below(Type number for line ):");
		for(int i=0;i<lines.length;i++) {
			System.out.print(Integer.toString(i+1) + ") " + lines[i].getName() + "; " );
		}
		int taLine = scan.nextInt()-1;
		System.out.println("Choose a target station from the list below (Type number of station):");
		for(int i=0;i<lines[taLine].getStations().length;i++) {
			System.out.print(Integer.toString(i+1) + ") " + lines[taLine].getStations()[i] + "; " );
		}
		System.out.println();
		int taStation = scan.nextInt()-1;
		String taStationName = lines[taLine].getStations()[taStation];
		scan.close();
		System.out.println("------------------------------------------------------------------------------------------");
		System.out.println("GOING FROM " + stStationName.toUpperCase() + " TO " + taStationName.toUpperCase() + "...");
		System.out.println("------------------------------------------------------------------------------------------");
		System.out.println("CALCULATING ROUTE...");
		System.out.println("------------------------------------------------------------------------------------------");

		//teearvutamine algab siit. Tehakse kaks korda (algusest ja lõpust), sest (mingil põhjusel) vahepeal toimub et ta teeb viga arvutamisel ühe suunda 
		
		routeBuilder test = new routeBuilder(lines,stLine,stStation,taLine,taStation);
		//routeBuilder test2 = new routeBuilder(lines,taLine,taStation,stLine,stStation);

		System.out.println("NUMBER OF STATIONS TO TRAVEL:");
		int a = test.MatriksiKoostamine();
		/*int b = test2.MatriksiKoostamine();
		if (a > b) {						//samal pohjusel kui kirjeldatud enne 
			System.out.println(b);
		}
		else {		*/
			System.out.println(a);
		//}
		System.out.println("------------------------------------------------------------------------------------------");
		test.TeeLeidmine();
		//test2.TeeLeidmine();
		
		System.out.println("------------------------------------------------------------------------------------------");
		System.out.println("ESTIMATED TIME ENROUTE:");
		/*if (a > b) {
			System.out.println(b*2.5);
		}else {		*/
			System.out.println(a*2.5);
		//}
		System.out.println("------------------------------------------------------------------------------------------");

	}

}


//1 5 - 5 7 rabotaet huevo, s4itaet odnu liwnuju stanciju. daze esli etu zhe stanciju ukazat 4erez druguju liniju, vse rabotaet normalno