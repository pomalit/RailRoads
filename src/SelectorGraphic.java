import java.util.ArrayList; 
import javafx.animation.FillTransition; 
import javafx.animation.Timeline; 
import javafx.application.Application; 
import javafx.event.EventHandler; 
import javafx.scene.Group; 
import javafx.scene.Scene; 
import javafx.scene.effect.DropShadow; 
import javafx.scene.effect.ImageInput; 
import javafx.scene.image.Image; 
import javafx.scene.input.KeyCode; 
import javafx.scene.input.KeyEvent; 
import javafx.scene.input.MouseButton; 
import javafx.scene.paint.Color; 
import javafx.scene.paint.Paint; 
import javafx.scene.shape.Rectangle; 
import javafx.stage.Stage; 
import javafx.util.Duration; 


public class SelectorGraphic extends Application { 
	private static int stLine; 
	private static int stStation; 
	private static int taLine; 
	private static int taStation; 
	private static ArrayList<String> route = new ArrayList<>(); 
	private static int rtLen = 0; 
	private static Paint a =new Color(0, 0, 0, 0); 
	private static double b = 0.0;

	public static int getStLine() { 
		return stLine; 
	} 
	public static void setStLine(int stLine) { 
		SelectorGraphic.stLine = stLine; 
	} 
	public static int getStStation() { 
		return stStation; 
	} 
	public static void setStStation(int stStation) { 
		SelectorGraphic.stStation = stStation; 
	} 
	public static int getTaLine() { 
		return taLine; 
	} 
	public static void setTaLine(int taLine) { 
		SelectorGraphic.taLine = taLine; 
	} 
	public static int getTaStation() { 
		return taStation; 
	} 
	public static void setTaStation(int taStation) { 
		SelectorGraphic.taStation = taStation; 
	} 
	public static ArrayList<String> getRoute() { 
		return route; 
	} 
	public static void setRoute(ArrayList<String> route) { 
		SelectorGraphic.route = route; 
	} 
	public static int getRtLen() { 
		return rtLen; 
	} 
	public static void setRtLen(int rtLen) { 
		SelectorGraphic.rtLen = rtLen; 
	} 
	@Override 
	public void start(Stage primaryStage) { 

		metroLine M1 = new metroLine("M1 - Red", new String[]{"Devyatkino","Grazhdansky Prospekt","Akademicheskaya","Politekhnicheskaya","Ploschad Muzhestva","Lesnaya","Vyborgskaya","Ploshchad Lenina","Chernyshevskaya","Ploshchad Vosstaniya/Mayakovskaya","Vladimirskaya/Dostoevskaya","Pushkinskaya/Zvenigorodskaya","Tekhnologichesky Institut","Baltiyskaya","Narvskaya","Kirovsky Zavod","Avtovo","Leninsky Prospekt","Prospekt Veteranov"}); 
		metroLine M2 = new metroLine("M2 - Blue", new String[]{"Parnas","Prospect Prosvesheniya","Ozerki","Udelnaya","Pionerskaya","Chyornaya Rechka","Petrogradskaya","Gorkovskaya","Nevsky Prospekt/Gostiny Dvor","Sennaya Ploschad/Spasskaya/Sadovaya","Tekhnologichesky Institut","Frunzenskaya","Moskovskiye Vorota","Elektrosila","Park Pobedy","Moskovskaya","Zvyozdnaya","Kupchino"}); 
		metroLine M3 = new metroLine("M3 - Green", new String[]{"Primorskaya","Vasileostrovskaya","Nevsky Prospekt/Gostiny Dvor","Ploshchad Vosstaniya/Mayakovskaya","Ploshchad Alexandra Nevskogo","Yelizarovskaya","Lomonosovskaya","Proletraskaya","Obukhovo","Rybatskoe"}); 
		metroLine M4 = new metroLine("M4 - Orange", new String[]{"Sennaya Ploschad/Spasskaya/Sadovaya","Vladimirskaya/Dostoevskaya","Ligovsky Prospekt","Ploshchad Alexandra Nevskogo","Novocherkasskaya","Ladozhskaya","Prospekt Bolshevikov","Ulitsa Dybenko"}); 
		metroLine M5 = new metroLine("M5 - Violet", new String[]{"Komendantskiy Prospekt","Staraya Derevnya","Krestovsky Ostrov","Chkalovskaya","Sportivnaya","Admiralteyskaya","Sennaya Ploschad/Spasskaya/Sadovaya","Pushkinskaya/Zvenigorodskaya","Obvodny Kanal","Volkovskaya","Bukharestskaya","Mezhdunarodnaya"}); 

		metroLine[] lines = {M1,M2,M3,M4,M5}; 

		setStLine(1); 
		setStStation(3); 
		setTaLine(2); 
		setTaStation(4); 

		//------------------------------------------------------------------------------------— 

		Group map = new Group(); 
		Image bg = new Image("file:metro.jpg", 875, 692, true, true, true); 
		ImageInput bgplace = new ImageInput(bg); 
		Rectangle bgbase = new Rectangle(); 
		bgbase.setEffect(bgplace); 
		map.getChildren().add(bgbase); 

		//graphic effects//-------------------------------------------------------------------— 

		FillTransition ft = new FillTransition(Duration.millis(500),Color.ALICEBLUE, Color.WHITE); 
		ft.setCycleCount(Timeline.INDEFINITE); // lõpmatu tsüklite arv 
		ft.setAutoReverse(true); // lõppu jõudes tagasi, algusest jälle edasi 
		ft.play(); // animatsioon mängima 

		DropShadow shdwStart = new DropShadow(20,0,0, Color.DARKGREEN); 
		DropShadow shdwTarget = new DropShadow(20,0,0, Color.BLACK); 
		DropShadow shdwRoute = new DropShadow(20,0,0, Color.DARKGOLDENROD); 

		//buttons//---------------------------------------------------------------------------— 

		StationButton[] l1 = {new StationButton(442,54,Color.RED),new StationButton(442,82,Color.RED),new StationButton(442,110,Color.RED), 
				new StationButton(442,138,Color.RED),new StationButton(442,166,Color.RED),new StationButton(442,194,Color.RED), 
				new StationButton(442,222,Color.RED),new StationButton(442,250,Color.RED),new StationButton(442,278,Color.RED), 
				new StationButton(442,304,Color.RED),new StationButton(442,378,Color.RED),new StationButton(380,440,Color.RED), 
				new StationButton(304,440,Color.RED),new StationButton(262,466,Color.RED),new StationButton(235,495,Color.RED), 
				new StationButton(208,521,Color.RED),new StationButton(180,550,Color.RED),new StationButton(166,580,Color.RED), 
				new StationButton(166,608,Color.RED)}; 
		StationButton[] l2 = {new StationButton(304,83,Color.BLUE),new StationButton(304,111,Color.BLUE),new StationButton(304,138,Color.BLUE), 
				new StationButton(304,166,Color.BLUE),new StationButton(304,194,Color.BLUE),new StationButton(304,221,Color.BLUE), 
				new StationButton(304,249,Color.BLUE),new StationButton(304,277,Color.BLUE),new StationButton(304,304,Color.BLUE), 
				new StationButton(304,387,Color.BLUE),new StationButton(304,440,Color.BLUE),new StationButton(304,498,Color.BLUE), 
				new StationButton(304,525,Color.BLUE),new StationButton(304,553,Color.BLUE),new StationButton(304,581,Color.BLUE), 
				new StationButton(304,608,Color.BLUE),new StationButton(304,636,Color.BLUE),new StationButton(304,663,Color.BLUE)}; 
		StationButton[] l3 = {new StationButton(28,330,Color.GREEN),new StationButton(101,330,Color.GREEN),new StationButton(304,330,Color.GREEN), 
				new StationButton(442,330,Color.GREEN),new StationButton(580,378,Color.GREEN),new StationButton(580,470,Color.GREEN), 
				new StationButton(580,497,Color.GREEN),new StationButton(580,525,Color.GREEN),new StationButton(580,553,Color.GREEN), 
				new StationButton(580,580,Color.GREEN)}; 
		StationButton[] l4 = {new StationButton(287,404,Color.ORANGE),new StationButton(442,404,Color.ORANGE),new StationButton(511,404,Color.ORANGE), 
				new StationButton(580,404,Color.ORANGE),new StationButton(649,404,Color.ORANGE),new StationButton(718,442,Color.ORANGE), 
				new StationButton(718,470,Color.ORANGE),new StationButton(718,497,Color.ORANGE)}; 
		StationButton[] l5 = {new StationButton(166,165,Color.PURPLE),new StationButton(166,193,Color.PURPLE),new StationButton(166,221,Color.PURPLE), 
				new StationButton(166,249,Color.PURPLE),new StationButton(166,276,Color.PURPLE),new StationButton(166,304,Color.PURPLE), 
				new StationButton(287,370,Color.PURPLE),new StationButton(380,466,Color.PURPLE),new StationButton(415,497,Color.PURPLE), 
				new StationButton(434,516,Color.PURPLE),new StationButton(442,553,Color.PURPLE),new StationButton(442,581,Color.PURPLE),}; 

		ArrayList<StationButton[]> buttons = new ArrayList<>(); 
		buttons.add(l1); 
		buttons.add(l2); 
		buttons.add(l3); 
		buttons.add(l4); 
		buttons.add(l5); 
		for(int i=0; i<buttons.size();i++) { 
			for(int j=0; j<buttons.get(i).length;j++) { 
				map.getChildren().add(buttons.get(i)[j]); 
			} 
		} 
		
		//button events//---------------------------------------------------------------------— 

		for(int i=0; i<buttons.size();i++) { 
			for(int j=0; j<buttons.get(i).length;j++) { 
				StationButton el = buttons.get(i)[j];
				el.assignLine=i;
				el.assignStation=j;
				el.setOnMousePressed(event -> { 
					if(event.getButton().equals(MouseButton.PRIMARY)) { 
						for(StationButton[] lstb:buttons) {
							for(StationButton button:lstb) {
								if(button.getEffect()==shdwStart||button.getEffect()==shdwRoute) {
									button.setEffect(null);
									button.setRadius(7);
								}
							}
						}
						el.setEffect(shdwStart); 
						el.setRadius(14);
						b=14;
						setStStation(el.assignStation); 
						setStLine(el.assignLine); 
					}
					else if(event.getButton().equals(MouseButton.SECONDARY)) { 
						for(StationButton[] lstb:buttons) {
							for(StationButton button:lstb) {
								if(button.getEffect()==shdwTarget||button.getEffect()==shdwRoute)
									button.setEffect(null);
							}
						}
						el.setEffect(shdwTarget); 
						el.setRadius(14);
						b=14;
						setTaStation(el.assignStation); 
						setTaLine(el.assignLine); 
					} 
				}); 
				buttons.get(i)[j].setOnMouseEntered(event ->{ 
					b = el.getRadius();
					el.setRadius(15); 
					a = el.getFill(); 
					ft.setFromValue((Color)el.getFill()); 
					ft.setShape(el); 
					ft.play(); 
				}); 
				buttons.get(i)[j].setOnMouseExited(event ->{ 
					el.setRadius(7); 
					ft.stop(); 
					ft.setShape(null); 
					el.setFill(a); 
					el.setRadius(b);
				}); 
			} 
		} 
		//-------------------------------------------------------------------------------------— 

		Scene scene1 = new Scene(map, 875,692); 
		scene1.setOnKeyPressed(new EventHandler<KeyEvent>() { 
			@Override
			public void handle(KeyEvent keyEvent) { 
				if (keyEvent.getCode() == KeyCode.ENTER) { 
					routeBuilder metroSys = new routeBuilder(lines,getStLine(),getStStation(),getTaLine(),getTaStation()); 
					setRtLen(metroSys.MatriksiKoostamine()); 
					System.out.println(getRtLen()*2.5); 
					setRoute(metroSys.TeeLeidmine()); 
					System.out.println(getRoute()); 
					for(int i=0; i<lines.length;i++) { 
						for(int j=0; j<lines[i].getStations().length;j++) { 
							if(metroSys.tee.contains(lines[i].getStations()[j])){
								buttons.get(i)[j].setEffect(shdwRoute);
								buttons.get(i)[j].setRadius(12);;								
							}
						}
					}
				} 
			} 
		});
		primaryStage.setScene(scene1); 
		primaryStage.setTitle("Saint Petersburg Metro Route Calculator"); 
		primaryStage.setResizable(false);
		primaryStage.show(); 
	} 

	public static void main(String[] args) { 
		launch(args); 
	} 
}