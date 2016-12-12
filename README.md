# SymfoniaFk
Pomocnicze raporty do systemu Sage Symfonia Finanse i Księgowość (R)

# Raporty użytkowe #

## Odblokowanie sald ujemnych ##

Po uruchomieniu raportu (*Raporty* / *Własne*) wszystkie wyciągi bankowe i raporty kasowe
będą mogły mieć ujemne salda. Pomocne np. przy rachunkach kredytowych.

Plik [`saldark.sc`](https://github.com/bonsoftpl/SymfoniaFk/blob/master/eksp_rap.sc).

# Raporty diagnostyczne

Raporty diagnostyczne przeznaczone są dla zaawansowanych
wdrożeniowców Symfonii, wskazane umiejętności programistyczne.
Konfiguracja tych raportów często polega na edycji kodu.

## Eksport raportów

Pozwala zapisać wszystkie raporty programu do plików tekstowych.

Linia `int bprg =` może występować w dwóch wariantach:
do raportów globalnych i lokalnych. Raporty lokalne są oddzielne
dla każdego roku.

Działa również dla ERP.

