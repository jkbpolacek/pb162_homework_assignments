Úloha č. 2: Vyhledávání v DOM
====================================

**Zveřejnění úlohy:** 5. Listopad 2017

**Deadline pro odevzdání:** 25. Listopad 2017

Obecné informace
-------------------
Cílem tohoto zadání je vytvořit jednoduchou [DOM](https://en.wikipedia.org/wiki/Document_Object_Model) 
(Document Object Model) strukturu, která se používá pro interní reprezentaci HTML stránek. Druhým cílem 
je vytvořit dotazovací engine podobný Sizzle enginu, který naleznete například v
[jQuery](https://en.wikipedia.org/wiki/JQuery).

Maximální počet bodů za tuto úlohu je **9**.

- **6 bodů** za procházející testy (přiložené testy negarantují 100% správnost).
- **2 body** za procházející rozšířené testy (mají k dispozici pouze cvičící).
- **1 bod** za čisté a elegantní řešení (konvence, minimální opakování kódu).

V případě, že **neprochází přiložené testy**, nelze získat více jak **5 bodů** (to znamená, že může být uděleno i **0 bodů**)!

### Požadavky
Pro řešení úlohy se předpokládají následující znalosti:

- Základní používání vyjímek. 
- Práce s kolekcemi, umět použít jejich metody.
- Korektní definování `equals` a `hashCode`.
- Správné používání `static` metod a proměnných.

### Struktura projektu
Struktura projektu poskytnutá jako základ pro vaši implementaci je rozdělena následujícím způsobem.

1. Balíček `cz.muni.fi.pb162.hw02` obsahuje třídy a rozhraní, která jsou součástí zadání nebo 
poskytují pomocné metody, které je vhodné v řešení využít.
  - **Neupravujte ani nepřidávejte do tohoto balíčku žádné další třídy nebo balíčky.**
2. Balíček  `cz.muni.fi.pb162.hw02.impl` je určen pro vaši implementaci.
  - **Vše mimo tento balíček bude při vyhodnocování řešení ignorováno.**
3. Balíček `cz.muni.fi.pb162.hw02.priv` obsahuje pomocné třídy, které využívá main metoda.
  - **Obsah balíčku není potřeba znát pro řešení úlohy.**

### Zadání
Následující zadání nepopisuje všechny metody, které máte implementovat, ale pouze účel tříd, jejich logické propojení, 
konstruktory a případně některé překryté metody z třídy `Object`. Detailní specifikaci metod naleznete v dokumentaci 
přiložených rozhraní.

Krok 1: DOM struktura
---------------------------
#### BaseAttribute
- V balíčku `cz.muni.fi.pb162.hw02.impl.dom` implementujte třídu `BaseAttribute`, která bude implementovat rozhraní
`Attribute`. Třída reprezentuje atributy HTML elementů. Například element 
`<div id="some-id"></div>` obsahuje jeden atribut `id`. Všechny atributy, které náleží k nějakému
elementu, jsou pak uloženy v kolekci typu `Set` na instanci třídy `BaseElement`. 

- Atribut se skládá z názvu a hodnoty. Třída bude mít následující konstruktory `BaseAttribute(String name)` 
a `BaseAttribute(String name, String value)`. Pokud je `name == null`, tak se vyvolá výjimka `IllegalArgumentException`.

- Překryjte metodu `boolean equals(Object obj)`. Dva atributy jsou si rovny, pokud mají stejný název. Nezapomeňte upravit
i metodu `int hashCode()`.

- Překryjte také metodu `String toString()`, která bude vracet řetězec ve tvaru `<name>="<value>"`.

#### BaseElement
- Ve stejném balíčku implementujte třídu `BaseElement`, která bude implementovat
rozhraní `Element`. Třída je určená pro reprezentaci jednotlivých elementů HTML dokumentu,
například `<div></div>`. Jedním z atributů třídy je kolekce instancí `Element`, která
je určena pro zachycení zanořování elementů. Kolekce bude obsahovat přímé potomky daného elementu.

- Implementujte konstruktory `BaseElement(String name)` a 
`BaseElement(String name, Set<Attribute> attributes, List<Element> childElements)`. Pokud bude libovolný parametr `null`,
tak vyvolejte výjimku `IllegalArgumentException`.

> Podívejte se na dokumentaci metody `split()` na třídě `String`.


Krok 2: Selektory
---------------------------
jQuery používá pro vyhledávání v DOM struktuře tzv. [CSS selektory](https://developer.mozilla.org/cs/docs/Web/CSS/CSS_Selectors).
Cílem dalšího kroku je implementovat třídy, které budou reprezentovat čtyři základní selektory. Třídy budou
v balíčku `cz.muni.fi.pb162.hw02.impl.selector` a každá bude implementovat rozhraní `Selector`.

#### ElementSelector
- Reprezentuje selektor podle názvu elementu. 

- Implementujte konstruktor `ElementSelector(String elementName)`.

- Metoda `Set<Element> apply(Set<Element> elements)` ze vstupní množiny vybere ty elementy, které mají
stejný název, jak název předaný konstruktoru selektoru.

#### ClassSelector
- Reprezentuje selektor podle třídy elementu.

- Implementujte konstruktor `ClassSelector(String className)`.

- Metoda `Set<Element> apply(Set<Element> elements)` ze vstupní množiny vybere ty elementy, které obsahují
třídu předanou konstruktoru selektoru.

#### IdSelector
- Reprezentuje selektor podle id elementu.

- Implementujte konstruktor `IdSelector(String id)`.

- Metoda `Set<Element> apply(Set<Element> elements)` ze vstupní množiny vybere ty elementy, které mají
hodnotu atributu `id` rovnu hodnotě předané konstruktoru selektoru.

#### DescendantSelector
- Nepatří mezi CSS selektory, ale kombinátory. Pro jednoduchost budeme ale považovat za selektor.
Reprezentuje výběr všech potomků.

- Metoda `Set<Element> apply(Set<Element> elements)` vrátí množinu všech potomků od všech předaných
elementů.

Krok 3: Parser dotazů
--------------------------------------
Dotazy pomocí selektorů se budou zadávat v textové podobě, například "div#my-id". Dalším úkolem je 
vytvořit parser, který takový dotaz převede na posloupnost tříd elementárních selektorů z předchozího kroku.

#### InvalidQueryException
- Vytvořte novou hlídanou výjimku v balíčku `cz.muni.fi.pb162.hw02.impl.parser`. Výjimka značí syntaktickou 
chybu při parsování dotazu.

#### QueryParserImpl
- Třída bude ve stejném balíčku `cz.muni.fi.pb162.hw02.impl.parser` a bude implementovat rozhraní
`QueryParser`.

- Implementujte konstruktor `QueryParserImpl(String query)`. Pokud je `query == null`, tak se
vyvolá výjimka `IllegalArgumentException`.


> Parsování je vhodné provádět postupně při příchodu požadavku na další selektor.

Krok 4: Provedení dotazu
--------------------------------------
Posledním krokem je implementovat třídu, která bude dotazy vyhodnocovat na stromě elementů.

#### QueryExecutor
- Třída bude v balíčku `cz.muni.fi.pb162.hw02.impl`.

- Třída bude statická, což znamená, že z ní nepůjde dědit a nepůjde z ní ani vytvářet instance.
Dědění zakážete modifikátorem `final` u třídy. Zakázat vytváření instancí lze, pokud není žádný
konstruktor viditelný.

- Implementujte statickou metodu `Set<Element> execute(String query, Element rootElement) 
throws InvalidQueryException`, která provede dotaz na kořenovém elementu získaný parametrem a na všech jeho 
potomcích. Provedením dotazu se zde myslí postupná aplikace všech selektorů, které se získají rozparsováním 
dotazu, na vstupní množinu elementů. Výslednou množinu metoda vrátí v návratové hodnotě.


> Nezapomeňte na veřejný defaultní konstruktor, který se vytvoří, pokud není žádný jiný konstruktor
implementován.

Test vyhledávání pomocí třídy App
--------------------------------------
Třída umožňuje spouštět dotazy nad stránkou [https://en.wikipedia.org/wiki/Java](https://en.wikipedia.org/wiki/Java_(programming_language)).
Stránka je stažená a uložená v `resources`. 

**Příklady dotazů:**
- `img` - vyhledá všechny obrázky na stránce
- `a.external` - vyhledá všechny odkazy s třídou `external`
- `div#mw-panel a.mw-wiki-logo` - vyhledá odkaz s logem wikipedie

Ukončin zadávání dotazů lze příkazem `exit`.