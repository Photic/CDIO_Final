package entity.deck;

import entity.player.Player;
import entity.player.PlayerList;

public class Deck {
	
	public Card[] deck = {
			new RecieveMoney("“Det er deres fødselsdag. Modtag af hver medspiller kr. 200.”", 0),
			new RecieveMoney("“Værdien af egen avl fra nyttehaven udgør kr. 200, som De modtager af banken.”", 200),
			new RecieveMoney("“De har vundet i klasselotteriet. Modtag kr. 500.”", 500),
			new RecieveMoney("“De havde en rekke med elleve rigtige i tipning. Modtag kr. 1.000.”", 1000),
			new RecieveMoney("“Grundet dyrtiden har De fået gageforhøjelse. Modtag kr. 1.000.”", 1000),
			new RecieveMoney("“Modtag udbytte af Deres aktier kr. 1.000.”", 1000),
			new RecieveMoney("“Deres præmieobligation er kommet ud. De modtager kr. 1.000 af banken”", 1000),
			new RecieveMoney("“De modtager Deres aktieudbytte. Modtag kr. 1.000 af banken.”", 1000),
			new RecieveMoney("“Modtag udbytte af deres aktier kr. 1.000.”", 1000),
			new RecieveMoney("“Komunen har eftergivet et kvartals skat. Hæv i banken kr. 3.000.”", 3000),
			new PayMoney("“De har måtte vedtage en parkeringsbøde. Betal kr. 200 i bøde.”", 200),
			new PayMoney("“De har været en tur i udlandet og haft for mange cigaretter med hjem. Betal told kr. 200.”", 200),
			new PayMoney("“De har kørt frem for “Fuld Stop”. Betal kr. 1.000 i bøde.”", 1000),
			new PayMoney("“Betal Dere bilforsikring kr. 1.000.”", 1000),
			new PayMoney("“De har modtaget Deres tandlægeregning. Betal kr. 2.000.”", 2000),
			new PayMoney("“De har modtaget Deres tandlægeregning. Betal kr. 2.000.”", 2000),
			new PayMoney("“Betal kr. 3.000 for reperation af Deres vogn.”", 3000),
			new PayMoney("“De modtager “Matador-legatet for værdi trængende”, stort kr. 40.000. "
						+ "Ved værdig trængende forstås, at Deres formue, d.v.s. "
						+ "Deres kontante penge + skøder + bygninger ikke overstiger kr. 15.000.”", 0),
			new PayMoney("“Ejendomsskatterne er steget, ekstraudgifterne er: kr. 800 pr. hus, kr. 2.300 per hotel”", 0),
			new PayMoney("“Oliepriserne er steget, og De skal betale: kr. 500 pr. hus, kr. 2.000 per. hotel”", 0),
			new JailCard("“I anledning af kongens fødselsdag benådes De herved for fængsel. "
						+ "Dette kort kan opbevares, indtil De får brug for det, eller De kan sælge det.”", 1),
			new JailCard("“I anledning af kongens fødselsdag benådes De herved for fængsel. "
						+ "Dette kort kan opbevares, indtil De får brug for det, eller De kan sælge det.”", 1),
			new JailCard("“Gå i fængsel. Ryk direkte til fængslet. "
						+ "Selv om De passerer “Start”, indkasserer De ikke kr. 4.000.”", 0),
			new JailCard("“Gå i fængsel. Ryk direkte til fængslet. "
						+ "Selv om De passerer “Start”, indkasserer De ikke kr. 4.000.”", 0),
			new MovePlayer("“Ryk brikken frem til det nærmeste rederi og betal ejeren to gange den leje, "
						+ "han ellers er berettiget til. Hvis selskabet ikke ejes af nogen kan De købe det af banken.”", 0),
			new MovePlayer("“Ryk brikken frem til det nærmeste rederi og betal ejeren to gange den leje, "
						+ "han ellers er berettiget til. Hvis selskabet ikke ejes af nogen kan De købe det af banken.”", 0),
			new MovePlayer("“Ryk tre felter tilbage.”", 3),
			new MovePlayer("“Ryk frem til “Start””", 0),
			new MovePlayer("“Tag med Mols-Linien - Flyt brikken frem, og hvis De passerer “Start”, inkassér da kr. 4.000.”", 4000),
			new MovePlayer("“Tag ind på Rådhuspladsen.”", 0),
			new MovePlayer("“Ryk frem til Grønningen. Hvis De passerer “Start”, indkassér da kr. 4.000.”", 4000),
			new MovePlayer("“Ryk frem til Frederiksberg Allé. Hvis De passerer “Start, indkassér kr. 4.000.”", 0)
	};
	
	/**
	 * Function to shuffle any objevt array.
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
	public void pickACard(Player p, PlayerList plist) {
		this.deck[0].cardFunction(p, plist);
		
		Card[] tempDeck = new Card[this.deck.length];
		tempDeck[this.deck.length-1] = this.deck[0];
		
		for (int i = 0; i < this.deck.length-1; i++)
			tempDeck[i] = this.deck[i+1];
		
		this.deck = tempDeck;
	}
	
	/**
	 * Gives a specifik card.
	 * @param a
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
