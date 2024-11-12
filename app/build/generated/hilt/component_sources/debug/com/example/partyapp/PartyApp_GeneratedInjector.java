package com.example.partyapp;

import dagger.hilt.InstallIn;
import dagger.hilt.codegen.OriginatingElement;
import dagger.hilt.components.SingletonComponent;
import dagger.hilt.internal.GeneratedEntryPoint;

@OriginatingElement(
    topLevelClass = PartyApp.class
)
@GeneratedEntryPoint
@InstallIn(SingletonComponent.class)
public interface PartyApp_GeneratedInjector {
  void injectPartyApp(PartyApp partyApp);
}
