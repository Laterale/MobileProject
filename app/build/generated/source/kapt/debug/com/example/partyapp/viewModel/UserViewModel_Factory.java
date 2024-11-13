// Generated by Dagger (https://dagger.dev).
package com.example.partyapp.viewModel;

import com.example.partyapp.data.repository.UserRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class UserViewModel_Factory implements Factory<UserViewModel> {
  private final Provider<UserRepository> repositoryProvider;

  public UserViewModel_Factory(Provider<UserRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public UserViewModel get() {
    return newInstance(repositoryProvider.get());
  }

  public static UserViewModel_Factory create(Provider<UserRepository> repositoryProvider) {
    return new UserViewModel_Factory(repositoryProvider);
  }

  public static UserViewModel newInstance(UserRepository repository) {
    return new UserViewModel(repository);
  }
}