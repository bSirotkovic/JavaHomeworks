# 1. domaca zadaca

**Prije no �to krenete dalje** , procitajte uputu na kraju dokumenta.

## 1. Pocetno pode�avanje

Pokrenite _Eclipse_ i odaberite _workspace_ direktorij (ako cete koristiti neki drugi a ne onaj koji ste
vec napravili na predavanjima). Kad se _Eclipse_ pokrene, na disku ce napraviti taj direktorij, i u
njemu (vjerojatno) poddirektorij RemoteSystemsTempFiles kao i skriveni poddirektorij
.metadata.

Sljedece radite izvan _Eclipsa_ (izravno kroz alate operacijskog sustava).

1. U _Eclipse_ workspace-u na disku napravite novi direktorij:
    hw01-0000000000 (zamijenite nule Va�im JMBAGom)
2. U njega s Interneta skinite osnovnu verziju datoteke:
    pom.xml
    Istu otvorite u uredivacu teksta i promijenite groupID u
    hr.fer.zemris.java.jmbag0000000000 (zamijenite nule Va�im JMBAGom)
    te artifactID u
    hw01-0000000000 (zamijenite nule Va�im JMBAGom).
3. Potom napravite u direktoriju projekta jo� poddirektorije:
    src/main/java
    src/main/resources
    src/test/java

Stanje na disku trebalo bi odgovarati prikazanome na sljedecoj slici.

Sada se ponovno vratite u _Eclipse_. Odaberite iz izbornika File ? Import ... ? Maven ?
Existing Maven Projects, Next, kao Root directory (desno klik na gumb Browse...)
odaberite direktorij projekta za domacu zadacu (hw01-xxxxxxxxxx). U popisu projekata koji ce se
potom prikazati oznacite (ako vec nije) /pom.xml koji odgovara ovoj domacoj zadaci (bit ce Vam
prikazan groupID:artifactId - provjerite je li u oba korektno evidentiran Va� JMBAG; ako nije,
Cancel pa izvana u editoru korigirajte pom.xml i potom ponovite postupak Import). Ako je sve u
redu, klik na gumb Finish.

Trebali biste dobiti prikaz kao na sljedecoj slici.


_Maven_ projekt time ce se prikazati u _Project Exploreru_ (lijevi dio prozora u _Java EE_ perspektivi).
Ekspandirajte projekt pa dvoklik na pom.xml i kad se otvori onaj graficki prikaz, pri dnu cete imati
karticu "pom.xml" - klik na nju i prikazat ce se tekst datoteke pom.xml. Unutra dodajte sekciju
<dependencies>, i unutar toga jedan <dependency> prema groupId:artifactId:version
postavljeni na junit:junit:4.12 (svaki se element pi�e kao zaseban tag) i uz scope postavljen na
test. Potom spremite izmjene (CTRL+S).

Trebali biste dobiti prikaz kao na sljedecoj slici. Uocite da ce u stavkama projekta pojaviti i sekcija
_Maven Dependencies_ , i unutar toga dvije biblioteke (junit-4.12.jar jer smo je tra�ili te
hamcrest-core-1.3.jar jer biblioteka junit-4.12.jar ovisi o njoj odnosno koristi razrede koji
su u njoj definirani).

Sada ste spremni za rje�avanje zadataka koji slijede.

Kada radite prvi program, napravite mi�em desni klik u _Project Exploreru_ na naziv direktorija u
koji treba smjestiti izvorni kod (*.java datoteke) � u na�em slucaju na src/main/java, pa u
iskocnom izborniku odaberite New ? Class. U prozoru koji se otvori, pri vrhu imate mjesto gdje
mo�ete upisati naziv paketa u koji treba smjestiti razred/program a malo ispod toga i mjesto gdje
mo�ete upisati naziv razreda/programa.


Prilikom pisanja koda, pridr�avajte se sljedecih konvencija.

- Nazivi razreda/sucelja/enumova uvijek se pi�u velikim pocetnim slovom. Ako su
    konkatenacija vi�e rijeci, svaka sljedeca opet zapocinje velikim pocetnim slovom. Npr.
    Student, StudentMail, StudentCourseEnrolment.
- Nazivi metoda uvijek se pi�u malim pocetnim slovom. Ako su konkatenacija vi�e rijeci,
    svaka sljedeca opet zapocinje velikim pocetnim slovom. Npr. print, printGrades,
    printStudentGrades. Nikada nemojte kratiti imena (primjerice izbacivanjem suglasnika,
    �to zna biti praksa u C kodu; dakle ne: prnt, prntGrds i slicno). Takoder, rijeci nikad ne
    spajajte podvlakama (takoder praksa iz C-a); znaci ne: print_student_grades.
- Nazivi varijabli slijede istu konvenciju kao i metode: dayOfMonth, monthOfYear, itd.
- Varijable deklarirajte uvijek tamo gdje ih prvi puta trebate. Nemojte nikada deklarirati sve
    �to Vam treba na pocetku metode. Varijable najce�ce inicijalizirate odmah pri deklaraciji,
    Dakle, ne:
    int month;
    month = 7;
    vec
    int month = 7;
    Takoder, ne:
    int month;
    month = calculateMonth();
    vec:
    int month = calculateMonth();
- Doseg varijable treba biti �to je manji (u�i) moguc. Ovo je korektno:
    int i;
    for(i = 0; i < 10; i++) {
    ...
    }
    ali ako nam taj i ne treba iza petlje for, onda je puno bolje:
    for(int i = 0; i < 10; i++) {
    ...
    }
- Nove varijable mogu se definirati i unutar blokova. Primjerice, ako nam treba varijabla koja
    postoji samo u tijelu naredbe if, tamo je deklarirajte. Npr.
    if(month > 6) {
    double bonusFactor = 1.2;
    ... radi ne�to s bonusFactor ...
    }
- Obavezno korentno indentirajte kod!
- Obavezno pi�ite Javadoc kako smo objasnili na predavanju.
- Uocite kako smo u prethodnim primjerima radili s viticastim zagradama. Viticasta zagrada
    otvara se u nastavku (u istom retku, nakon jedne praznine) naredbe kojoj taj blok pripada
    (pogledajte gore primjer naredbi if i for), u sljedecim retcima uvuceno dolaze naredbe koje
    pripadaju tom bloku, i potom u novom retku vizualno poravnatno okomito prema gore s
    prvim slovom naredbe kojoj blok pripada dolazi zatvorena viticasta zagrada (kod if-a je
    poravnata sa slovom i, kod for-a sa slovom f).
- Naredbe if, for, do i while uvijek pi�ite s pripadnim blokovima, cak ako je unutra i samo
    jedna naredba. Dakle, ne:
    if(a > b) printSomething();
    vec:
    if(a > b) {
    printSomething();
    }


- Testove uvijek smje�tajte u src/test/java. Naziv uobicajeno odgovara nazivu razreda
    koji se testira sa sufiksom Test; npr. za razred P07 testovi su u P07Test. Metode koje test
    poziva iz testiranog razreda ne smiju biti private.

**Zadatak 1.**

U paketu hr.fer.zemris.java.hw01 napravite program Factorial. Program se pokrece bez
argumenata. Korisnik preko tipkovnice unosi cijele brojeve u rasponu od 1 do 20. Ako korisnik
zada broj koji je izvan tog raspona ili ako nije broj, ispisati odgovarajucu poruku i nastaviti dalje s
radom. Program ispisuje faktorijelu zadanog broja. Odaberite za izracun tip podatka uz koji cete
moci izracunati rezultat za ovaj raspon brojeva. Evo ocekivanog primjera interakcije programa i
korisnika, nakon �to se program pokrene:

Unesite broj > �tefica
'�tefica' nije cijeli broj.
Unesite broj > 1
1! = 1
Unesite broj > 3.
'3.14' nije cijeli broj.
Unesite broj > -
'-4' nije broj u dozvoljenom rasponu.
Unesite broj > 4
4! = 24
Unesite broj > kraj
Dovidenja.

Rad programa prekida se kada korisnik unese "kraj". Korisnikovi unosi prikazani su crvenom
bojom. Va� bi program za identicne ulaze iz primjera trebao generirati upravo prikazani ispis.

Izracun faktorijele ostvarite u zasebnoj pomocnoj metodi (slo�enost nije bitna). Napi�ite testove za
tu metodu.

**Zadatak 2.**

U paketu hr.fer.zemris.java.hw01 napravite program Rectangle. Program pita korisnika preko
naredbenog retka da unese �irinu pa visinu pravokutnika. Program korisniku ispisuje povr�inu i
opseg pravokutnika. Ako program pri pokretanju dobije argumente preko naredbenog retka, onda
ni�a ne pita korisnika, vec odmah racuna i ispisuje povr�inu i opseg. Korisnik kao �irinu i visinu
mo�e unijeti i decimalne brojeve. Primjerice, ako program pokrenemo ovako:

Rectangle 2 8

program ce odmah ispisati sljedece, i nakon toga prekinuti s radom.

Pravokutnik �irine 2.0 i visine 8.0 ima povr�inu 16.0 te opseg 20.0.

Ako ga pokrenemo bez argumenata, ocekivano je pona�anje ilustrirano ispisom u nastavku.

Unesite �irinu > �tefica
'�tefica' se ne mo�e protumaciti kao broj.
Unesite �irinu > -2.
Unijeli ste negativnu vrijednost.
Unesite �irinu > 2
Unesite visinu > �tefica
'�tefica' se ne mo�e protumaciti kao broj.
Unesite visinu > -2.


Unijeli ste negativnu vrijednost.
Unesite visinu > 8
Pravokutnik �irine 2.0 i visine 8.0 ima povr�inu 16.0 te opseg 20.0.

Prilikom pisanja programa nemojte sav kod nagurati u metodu main. Stvari koje se ponavljaju
izolirajte u pomocne metode pa ih pozivajte iz metode main koliko puta je potrebno.

Ako se programu preda broj argumenata koji je razlicit od 0 odnosno 2, program treba ispisati
prikladnu poruku i prekinuti s radom.

**Zadatak 3.**

U paketu hr.fer.zemris.java.hw01 napravite program UniqueNumbers. Program se pokrece bez
argumenata. U programu napravite pomocnu strukturu TreeNode koja ima clanske varijable left i
right, te value koji je tipa int (ne smije imati _ni�ta_ osim toga). Napravite u programu pomocne
metode koje dodaju cvor u uredeno binarno stablo (lijevo manji, desno veci), samo ako cvor sa
vrijednosti koja se dodaje vec ne postoji. Evo primjera uporabe.

TreeNode glava = null;
glava = addNode(glava, 42);
glava = addNode(glava, 76);
glava = addNode(glava, 21);
glava = addNode(glava, 76);
glava = addNode(glava, 35);

Ako ste to dobro, stablo ce imati cetiri cvora, i vrijedit ce:

glava.value: 42
glava.left.value: 21
glava.left.right.value: 35
glava.right.value: 76

Napravite metodu koja vraca velicinu stabla. Evo primjera uporabe.

int velicina = treeSize(glava);

Pozovemo li je nakon prethodnog primjera, rezultat bi morao biti 4. Pozovemo li je prije prvog
poziva addNode, rezultat bi morao biti 0.

Istestirajte obje metode.

Napravite metodu containsValue koja vraca nalazi li se tra�eni element u stablu. Primjerice,
sljedece bi nakon prethodnog primjera trebalo ispisati da.

if(containsValue(glava, 76)) {
System.out.println("da");
}

Napravite tu metodu i istestirajte je.

Potom napravite glavni program tako da od korisnika s tipkovnice cita broj po broj i dodaje ih u
stablo ako tamo vec ne postoje (�to se dogodilo, ispisuje na zaslon). Korisnik unos prekida
utipkavanjem "kraj". Jednom kad je unos gotov, program treba ispisati brojeve najprije sortirano
od manjeg prema vecem a potom u sljedecem retku od veceg prema manjem. Evo primjer
ocekivane interakcije.


Unesite broj > �tefica
'�tefica' nije cijeli broj.
Unesite broj > 3.
'3.14' nije cijeli broj.
Unesite broj > 42
Dodano.
Unesite broj > 76
Dodano.
Unesite broj > 21
Dodano.
Unesite broj > 42
Broj vec postoji. Preskacem.
Unesite broj > 35
Dodano.
Unesite broj > kraj
Ispis od najmanjeg: 21 35 42 76
Ispis od najveceg: 76 42 35 21

Ako zbog bolje organizacije koda trebate pomocne metode, slobodno ih napravite. Kako nije ba�
jednostavno testirati kod koji cita s tipkovnice odnosno pi�e na zaslon, taj dio niti nemojte testirati.
Testirajte samo metode koje ne rade interakciju s korisnikom.


**Napomena.**

Prilikom rje�avanje svih domacih zadaca, pa tako i ove, zadatke morate rje�avati samostalno.
Smijete se konzultirati s drugim polaznicima vje�tine PRIJE no �to krenete programirati (�to treba
rije�iti, koja je ideja, kako bi se rje�enje moglo oblikovati i slicno). Jednom kad krenete
programirati, du�ni ste sami samostalno napisati rje�enje. Ako ne�to tada zapne, pronadite me na
faksu i pitajte. Gledanje tudeg rje�enja domace zadace ili cak uporaba te krada dijelova koda i
ugradnja istoga u vlastitu domacu zadacu smatra se varanjem (bilo da ste nakon toga uhvaceni ili ne

- budite svjesni ako to radite, da to radite) i imat ce posljedice.

U ovoj prvoj zadaci ne smijete koristiti biblioteke koje nismo obradili; posebice ne smijete koristiti
_Java Collection Framework_ te gotove implementacije kolekcija. Ako niste sigurni smijete li ne�to
koristiti, postavite pitanje u Ferku na _Pitanja i odgovori_ (imate na stranici kolegija link gore na vrhu
ekrana u drugoj traci).

Procitajte u knjizi dio poglavlja 2 (stranice 21-32) te poglavlje 4 (stranice 75 do 91). Takoder,
procitajte na kraju dokumenta koji smo koristili na predavanju (uploadan je na Ferka pod
predavanja) jo� onih par primjera i napomena.

**Predaja domace zadace.**

Eclipse projekt potrebno je zapakirati u arhivu imena hw01-0000000000.zip (zamijenite nule
Va�im JMBAG-om). Obavezno provjerite da u tu arhivu NE zapakirate skriveni _Eclipseov_ direktorij
.settings, direktorij target (ili bilo �to �to se automatski gradi) kao niti datoteke .classpath i
.project koje stvara _Eclipse_. Sadr�aj arhive mora biti valjani Maven projekt (pri tome se ne pakira
vr�ni direktorij projekta, vec sadr�aj arhive mora biti odmah direktorij src, datoteka pom.xml i
slicno). Ovo mo�ete uciniti i izravno iz _Eclipsea_ : desni klik na projekt, Export... ? General ?
Archive File pa u prozoru koji se otvori odznacite kvacice uz .classpath i .project, ra�irite
direktorij projekta, odznacite kvacite uz .settings i target, u opcijama odaberite ZIP te "Create
only selected directories", i konacno odaberite gdje ce se napraviti arhiva i kako ce se zvati.
Jednom kad napravite arhivu, zatvorite _Eclipse_ , pokrenite ga ponovno i napravite novi pomocni
_workspace_. Provjerite mo�ete li importati napravljeni projekt i to izravno iz ZIP arhive. Potrebni
koraci za import u ovom su slucaju sljedeci.

File ? Import... ? General ? Projects from Folder or Archive, u prozoru koji se potom
otvori klik na gumb "Archive...", i odaberite napravljenu ZIP arhivu (na isti cete nacin importati
domace zadace koje cete trebati recenzirati). Nakon analize arhive, prikazat ce se informacija da je
pronaden Maven projekt. Klik na gumb Finish. Postupak ce sam, cim odaberete arhivu, istu
ekspandirati u _workspace_ direktorij (primjerice, ako je zip arhiva hw01-0000000000.zip, napravit
ce se direktorij hw01-0000000000.zip_expanded, i to ce postati i naziv projekta u _Project
Exploreru_ ). Ako na disku �elite smislenije ime, mo�ete sami u _workspace_ direktoriju
odkomprimirati arhivu pod "normalnim" imenom (npr. hw01-0000000000), i potom napraviti
izravno import Maven projekta.

Sav kod mora imati prikladan javadoc.

Rje�enje predajete na Ferka (idete na stranicu kolegija, pa _Komponente_ , _Domace zadace_ , _1. domaca
zadaca_ , _Upload_ ). Trebate uploadati ZIP arhivu. Nakon �to ste sigurni da je to konacno rje�enje,
upload morate zakljucati, nakon cega vi�e nece biti moguce raditi novi upload. Predaje koje nisu
zakljucane, smatraju se nepredanima. Rok za upload i zakljucavanje je cetvrtak, 09. o�ujka, u
7:00:00 ujutro.


