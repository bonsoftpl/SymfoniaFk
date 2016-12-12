//1005,"saldark.sc","Odblokowanie sald ujemnych",1.0,SYSTEM

#include "dbutil.sci"

int bdef = open buf=KatalogRoku()+FN_DEFDOK+".dat" for base "def_dok" : BaseError(bdef,4)

mapvalue mbWzor
  mbWzor.Set("6", 1)
  mbWzor.Set("14", 1)
  mbWzor.Set("21", 1) //RKW
int nRokOd = Val(firma.rok)
int nRokDo = Val(firma.rok)

int nRok

GetRec(bdef,FS)
while BaseError(bdef,0)==0
  if mbWzor.Index((using "%d", GetField(bdef,"dwzor"))) then
    print GetField(bdef,"dwzor"), GetField(bdef, "dskrot"); lf
    for nRok=nRokOd to nRok>nRokDo
      PutIni( (using "Raporty kasowe %d",nRok), GetField(bdef,"dskrot")+"_blokadaSaldaMa", "0", KatalogFirmy()+"firma.ini")
    next nRok
  endif
  GetRec(bdef,NX)
wend

message "Salda ujemne dla raportów kasowych i bankowych roku "+firma.rok+" zosta³y odblokowane"
close : error ""
