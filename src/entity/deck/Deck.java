package entity.deck;


	
public class Deck {
	
	public String[] fulldeck = { 
			"BirthdayCard", 
			"RecieveMoneyCard", 
			"PayMoneyCard", 
			"AntiJailCard", 
			"GoToJailCard", 
			"MovePlayerCard" 
			};
	
	public Card[] deck = {
			new BirthdayCard("“Det er deres fødselsdag. Modtag af hver medspiller kr. 200.”"),
			new RecieveMoneyCard("“Værdien af egen avl fra nyttehaven udgør kr. 200, som De modtager af banken.”", 200),
			new RecieveMoneyCard("“De har vundet i klasselotteriet. Modtag kr. 500.”", 500),
			new RecieveMoneyCard("“De havde en rekke med elleve rigtige i tipning. Modtag kr. 1.000.”", 1000),
			new RecieveMoneyCard("“Grundet dyrtiden har De fået gageforhøjelse. Modtag kr. 1.000.”", 1000),
			new RecieveMoneyCard("“Modtag udbytte af Deres aktier kr. 1.000.”", 1000),
			new RecieveMoneyCard("“Deres præmieobligation er kommet ud. De modtager kr. 1.000 af banken”", 1000),
			new RecieveMoneyCard("“De modtager Deres aktieudbytte. Modtag kr. 1.000 af banken.”", 1000),
			new RecieveMoneyCard("“Modtag udbytte af deres aktier kr. 1.000.”", 1000),
			new RecieveMoneyCard("“Komunen har eftergivet et kvartals skat. Hæv i banken kr. 3.000.”", 3000),
			new PayMoneyCard("“De har måtte vedtage en parkeringsbøde. Betal kr. 200 i bøde.”", 200),
			new PayMoneyCard("“De har været en tur i udlandet og haft for mange cigaretter med hjem. Betal told kr. 200.”", 200),
			new PayMoneyCard("“De har kørt frem for “Fuld Stop”. Betal kr. 1.000 i bøde.”", 1000),
			new PayMoneyCard("“Betal Dere bilforsikring kr. 1.000.”", 1000),
			new PayMoneyCard("“De har modtaget Deres tandlægeregning. Betal kr. 2.000.”", 2000),
			new PayMoneyCard("“De har modtaget Deres tandlægeregning. Betal kr. 2.000.”", 2000),
			new PayMoneyCard("“Betal kr. 3.000 for reperation af Deres vogn.”", 3000),
			new PayMoneyCard("“De modtager “Matador-legatet for værdi trængende”, stort kr. 40.000. "
						+ "Ved værdig trængende forstås, at Deres formue, d.v.s. "
						+ "Deres kontante penge + skøder + bygninger ikke overstiger kr. 15.000.”", 1),
			new PayMoneyCard("“Ejendomsskatterne er steget, ekstraudgifterne er: kr. 800 pr. hus, kr. 2.300 per hotel”", 2),
			new PayMoneyCard("“Oliepriserne er steget, og De skal betale: kr. 500 pr. hus, kr. 2.000 per. hotel”", 3),
			new AntiJailCard("“I anledning af kongens fødselsdag benådes De herved for fængsel. "
						+ "Dette kort kan opbevares, indtil De får brug for det, eller De kan sælge det.”"),
			new AntiJailCard("“I anledning af kongens fødselsdag benådes De herved for fængsel. "
						+ "Dette kort kan opbevares, indtil De får brug for det, eller De kan sælge det.”"),
			new GoToJailCard("“Gå i fængsel. Ryk direkte til fængslet. "
						+ "Selv om De passerer “Start”, indkasserer De ikke kr. 4.000.”", 0),
			new GoToJailCard("“Gå i fængsel. Ryk direkte til fængslet. "
						+ "Selv om De passerer “Start”, indkasserer De ikke kr. 4.000.”", 0),
			new MovePlayerCard("“Ryk brikken frem til det nærmeste rederi og betal ejeren to gange den leje, "
						+ "han ellers er berettiget til. Hvis selskabet ikke ejes af nogen kan De købe det af banken.”", 1),
			new MovePlayerCard("“Ryk brikken frem til det nærmeste rederi og betal ejeren to gange den leje, "
						+ "han ellers er berettiget til. Hvis selskabet ikke ejes af nogen kan De købe det af banken.”", 1),
			new MovePlayerCard("“Ryk tre felter tilbage.”", 2),
			new MovePlayerCard("“Ryk frem til “Start””", 3),
			new MovePlayerCard("“Tag med Mols-Linien - Flyt brikken frem, og hvis De passerer “Start”, inkassér da kr. 4.000.”", 4),
			new MovePlayerCard("“Tag ind på Rådhuspladsen.”", 5),
			new MovePlayerCard("“Ryk frem til Grønningen. Hvis De passerer “Start”, indkassér da kr. 4.000.”", 6),
			new MovePlayerCard("“Ryk frem til Frederiksberg Allé. Hvis De passerer “Start, indkassér kr. 4.000.”", 7)
	};
	
	/**
	 * Function to shuffle any object array.
	 * @param collectionOfCards
	 */
	public void shuffleCards() {
		for (int i = 0; i < this.deck.length; i++) {
			int s = i + (int)(Math.random() * (this.deck.length - i));
			Card temp = this.deck[s];
			this.deck[s] = this.deck[i];
			this.deck[i] = temp;
		}
	}
	
	/**
	 * Pick a card from the top of the deck.
	 * @param pickCardFromDeck
	 */
	public Card pickACard() {
		Card[] tempDeck = new Card[this.deck.length];
		tempDeck[this.deck.length-1] = this.deck[0];
		
		for (int i = 0; i < this.deck.length-1; i++)
			tempDeck[i] = this.deck[i+1];
		
		this.deck = tempDeck;
		
		return this.deck[this.deck.length];
	}

	/**
	 * Gives a specifik card.
	 * @param Takes a number 'a' and returns a specific card.
	 * @return
	 */
	public Card getCard(int a) {
		return this.deck[a];
	}
	
	/**
	 * Get length of array.
	 * @return
	 */
	public int getLength() {
		return this.deck.length;
	}
	
}

