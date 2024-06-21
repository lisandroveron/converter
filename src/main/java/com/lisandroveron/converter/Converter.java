package com.lisandroveron.converter;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.io.IOException;
import java.lang.InterruptedException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.net.http.HttpResponse;
import java.net.URI;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Converter {
  private static Rates rates;

  public static void main(String[] args) {
    URI uri = URI.create(
      "https://v6.exchangerate-api.com/v6/"
      + System.getenv("API_KEY")
      + "/latest/USD"
    );

    HttpClient client = HttpClient.newHttpClient();

    HttpRequest req = HttpRequest.newBuilder(uri).build();

    try {
      HttpResponse<String> res = client.send(req, BodyHandlers.ofString());

      Gson gson = new Gson();
      rates = gson.fromJson(res.body(), Rates.class);
    } catch (IOException | InterruptedException | JsonSyntaxException e) {
      System.out.println("No se pudo obtener los tipos de cambio.");
      e.printStackTrace();
      System.exit(0);
    } finally {
      client.close();
    };

    String menu =
    """
    -- -- -- -- -- Conversor de monedas -- -- -- -- --

    1. Convertir de USD a ARS
    2. Convertir de ARS a USD
    3. Convertir de USD a BRL
    4. Convertir de BRL a USD
    5. Convertir de USD a CLP
    6. Convertir de CLP a USD
    0. Salir
    """;

    Scanner scanner = new Scanner(System.in);

    System.out.println(menu);

    while (true) {
      try {
        System.out.println("Elija una opción:");
        Integer option = scanner.nextInt();

        if (option == 0) {
          break;
        };

        while (true) {
          System.out.println(
            "Introduzca el valor a convertir o 0 para elegir otra opción"
          );
          
          try {
            Float amount = scanner.nextFloat();

            if (amount == 0) {
              break;
            };

            switch (option) {
              case 1:
                System.out.println(convert(amount, "USD", "ARS"));
                break;
              case 2:
                System.out.println(convert(amount, "ARS", "USD"));
                break;
              case 3:
                System.out.println(convert(amount, "USD", "BRL"));
                break;
              case 4:
                System.out.println(convert(amount, "BRL", "USD"));
                break;
              case 5:
                System.out.println(convert(amount, "USD", "CLP"));
                break;
              case 6:
                System.out.println(convert(amount, "CLP", "USD"));
                break;
            };
          } catch (InputMismatchException e) {
            System.out.println("Introduzca solo números.");
            scanner.nextLine();
          };
        };
      } catch (InputMismatchException e) {
        System.out.println("Introduzca solo números.");
        scanner.nextLine();
      };
    };

    scanner.close();
  };

  private static Float convert(Float amount, String from, String to) {
    return amount
        / rates.conversionRates.get(from)
        * rates.conversionRates.get(to);
  };
};