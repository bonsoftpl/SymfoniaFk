//1005,"eksp_rap.sc","Eksport raport�w",3.00,SYSTEM
///////////////////////////////////////
// Eksport raport�w
// Autor: Jarek Czekalski, na podstawie oryginalnego raportu Symfonii
///////////////////////////////////////

int err
string kat_wyj, nazwa_wyj
int plik_wyj
limit 32767

string sub wczytaj_string (string prompt, string war_pocz)
string wynik = war_pocz
Form prompt, 400, 110
  Edit "", wynik, 80, 10, 200, 24
  Button "&OK", 20, 50, 80, 24, 2
  Button "&Anuluj", 300, 50, 80, 24, -1
int nRetV = execform
if nRetV==0 || nRetV==-1 then close : error ""
wczytaj_string = wynik
endsub

buf = firma.ver
delete regular "^20"
delete "." : delete "."
if Len(buf)==2 then buf += "0"
if Len(buf)>2 && Mid(buf,3,1)>="a" then buf = Mid(buf,1,2) + "0" + Mid(buf,3)

kat_wyj = wczytaj_string ("Podaj katalog wyj�ciowy:","s:\\raporty\\std\\fk\\fkp" + buf + "\\")
buf = kat_wyj
if !find regular "(\\)|(/:)$" then kat_wyj += "\\"
Mkdir(kat_wyj)

#ifdef FORTE
int bprg = Open KatalogFirmy() for base "PROGRAMY"
#else
int bprg = Open katalog()+"02system.dat" for base "PROGRAMY"
// int bprg = Open KatalogRoku()+"02lokal.dat" for base "PROGRAMY"
#endif
if !bprg then error "B��d przy otwieraniu bazy PROGRAMY !!!"

SetKey (bprg, "skrot")

err = GetRec (bprg, FS)
while !err
  buf = GetField(bprg,"skrot")
  delete regular at "?##\\"
  nazwa_wyj = buf
  buf = GetField(bprg,"dane")
  if find regular at "////[0-9]++,\"{*}\",\"*\"" then nazwa_wyj = regular 1
  if nazwa_wyj == "" then nazwa_wyj = wczytaj_string ("Podaj nazw� dla " + GetField(bprg,"skrot"),"")
  print kat_wyj+nazwa_wyj,GetField(bprg,"skrot"), GetField(bprg,"idcomp"), "..."
  if nazwa_wyj && (GetField(bprg,"idcomp")==0) then
    plik_wyj = 0
    plik_wyj = open kat_wyj+nazwa_wyj for binary output
    if plik_wyj==0 then
      print "Nie uda�o si� zachowa� pliku ";kat_wyj;nazwa_wyj;lf
    else
      print #plik_wyj; buf
      close plik_wyj
      print "Zapisany"
    endif
  endif
  print lf
  err = GetRec (bprg, NX)
wend
