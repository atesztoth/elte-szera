# Szeráj
## Szállodai üzenet management

Az alkalmazás célja lehetővé tenni szállodai vendégek számára üzenetek küldését 
érdeklődők felé harmadik félen keresztül (recepciós).

## _About_
### Endpoints
#### Dummy endpoints:
Ezeknek célja gyors teszt felhasználók létrehozása.
`/create-friend`
Nagyon egyszerű payload: 

`{ "name": "Friend name"}`

Két fő user endpontjai:\
`/create-guest` `/create-recepcionist`

Payload: \
```
   {
   	"username": "felhasználónév",
   	"password": "jelszó",
   	"firstName": "Keresztnév",
   	"lastName": "Vezetéknév"
   }
```

### Authentikáció
`/login` endpoint alatt. From data-t vár, egy usernevet és egy jelszót. \
Két féle ROLE-t szerezhetünt: `GUEST`, vagy `RECEPCIONIST`.

### Guest endpointok
- `/messages` - üzenetek lekérése
- `/message/{mid}` POST: Üzenet törlése
- `/message/{mid}` GET: Üzenet olvasása
- `/message` POST: Üzenet lérehozása (1)

(1): Payload:

```
{
	"message": "üzenet",
	"friend": { "id": #target-id },
	"attachedPhoneNumber": "telefonszám"
}
```

### Receptionist endpointok
- `/messages/{gid}/{fid}` GET: Megjeleníti az érdeklődőnek szánt üzeneteket.
- `/messages/{gid}/{fid}` POST: Felad egy üzenetet egy GUEST számára.

## Futtatás
Adatbázis dockerrel: `docker-compose -f ./docker-compose.yml up` \
Ez elindít egy mysql adatbázist. \
`mvn spring-boot:run` \
A backend a 10010-es porton érhető majd el.

