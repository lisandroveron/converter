# Converter

The **Converter** project is a Java application designed to convert monetary values between different currencies using real-time exchange rates. It utilizes the Exchange Rate API to fetch the latest exchange rates and allows users to select desired conversions from and to various major currencies.

## Key features

* **Fetching Exchange Rates:** The application connects to the ExchangeRate-API to retrieve the most recent exchange rates relative to the United States Dollar (USD).

* **Interactive User Interface:** It presents an interactive menu that enables users to choose from various conversion options, such as USD to ARS, ARS to USD, USD to BRL, and others.

* **Data Input and Validation:** Accepts user input for the amount to convert and performs validations to ensure only valid numbers are entered.

* **Currency Conversion:** Uses the fetched rates to accurately convert the entered amount from one currency to another.

## For developers.

**Note:** You need an API key from [Exchange Rate](https://www.exchangerate-api.com/) before proceed.

### Dependencies

* Maven (v3.9.7 or greater)

* Gson (v2.11.0 or greater)

### Compile and run

Compilation command:

```bash
$ mvn compile
```

Run command:
```bash
$ API_KEY=your_exchangerate-api_key mvn exec:java -Dexec.mainClass="com.lisandroveron.converter.Converter"
```