# RabbitMQ + Quarkus

## Uruchomienie RabbitMQ

Jeżeli używasz pliku `rabbitmq.yml`:

``` bash
docker compose -f rabbitmq.yml up -d
```

Sprawdzenie statusu:

``` bash
docker compose -f rabbitmq.yml ps
```

Logi:

``` bash
docker compose -f rabbitmq.yml logs -f
```

Zatrzymanie:

``` bash
docker compose -f rabbitmq.yml down
```

------------------------------------------------------------------------

## Panel administracyjny RabbitMQ

Po uruchomieniu kontenera otwórz przeglądarkę:

http://localhost:15672

Domyślne dane logowania:

-   **Login:** `guest`
-   **Hasło:** `guest`

W panelu możesz: - przeglądać Exchange, - przeglądać kolejki (Queues), -
publikować wiadomości, - obserwować liczbę wiadomości i konsumentów.

------------------------------------------------------------------------

## Znaczenie parametrów w `application.properties`

``` properties
rabbitmq.host=localhost
rabbitmq.port=5672
rabbitmq.username=guest
rabbitmq.password=guest

mp.messaging.incoming.transactions.connector=smallrye-rabbitmq
mp.messaging.incoming.transactions.host=${rabbitmq.host}
mp.messaging.incoming.transactions.port=${rabbitmq.port}
mp.messaging.incoming.transactions.username=${rabbitmq.username}
mp.messaging.incoming.transactions.password=${rabbitmq.password}

mp.messaging.incoming.transactions.exchange.name=transactions
mp.messaging.incoming.transactions.queue.name=transactions-queue
```

### Parametry połączenia

Parametr              Opis
  --------------------- -------------------------------
`rabbitmq.host`       Adres serwera RabbitMQ.
`rabbitmq.port`       Port AMQP (domyślnie `5672`).
`rabbitmq.username`   Nazwa użytkownika.
`rabbitmq.password`   Hasło użytkownika.

### Konfiguracja kanału

  ---------------------------------------------------------------------------
Parametr                                         Opis
  ------------------------------------------------ --------------------------
`mp.messaging.incoming.transactions.connector`   Określa, że kanał
`transactions` korzysta z
RabbitMQ.

`host`                                           Host RabbitMQ dla tego
kanału.

`port`                                           Port RabbitMQ.

`username`                                       Login do RabbitMQ.

`password`                                       Hasło do RabbitMQ.

`exchange.name`                                  Nazwa exchange, z którego
będą odbierane wiadomości.

`queue.name`                                     Nazwa kolejki, z której
będzie czytał Quarkus.
  ---------------------------------------------------------------------------

------------------------------------------------------------------------

## Powiązanie z Consumerem

``` java
@Incoming("transactions")
public void process(Buffer message) {
    ...
}
```

Nazwa w adnotacji `@Incoming("transactions")` musi odpowiadać nazwie
kanału w konfiguracji:

``` properties
mp.messaging.incoming.transactions...
```

------------------------------------------------------------------------

## Przepływ wiadomości

``` text
Producer
    |
    v
Exchange (transactions)
    |
    v
Queue (transactions-queue)
    |
    v
@Incoming("transactions")
```

------------------------------------------------------------------------

## Wysłanie wiadomości z GUI

1.  Wejdź do **Exchanges**.
2.  Otwórz exchange **transactions**.
3.  Rozwiń sekcję **Publish message**.
4.  Wklej przykładowy JSON:

``` json
{
  "transactionId": "123",
  "amount": 100,
  "currency": "PLN"
}
```

5.  W **Properties** ustaw:

```{=html}
<!-- -->
```
    content_type = application/json

6.  Kliknij **Publish message**.

Jeżeli aplikacja działa poprawnie, wiadomość zostanie odebrana przez
metodę oznaczoną `@Incoming("transactions")`.
