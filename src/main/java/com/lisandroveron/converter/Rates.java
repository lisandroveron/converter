package com.lisandroveron.converter;

import com.google.gson.annotations.SerializedName;
import java.util.Map;

final class Rates {
  @SerializedName("conversion_rates")
  public final Map<String, Float> conversionRates;

  Rates(Map<String, Float> conversionRates) {
    this.conversionRates = conversionRates;
  };
};