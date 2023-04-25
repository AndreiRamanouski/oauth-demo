package com.appdev.remote.storage.provider;

import lombok.extern.slf4j.Slf4j;
import org.keycloak.component.ComponentModel;
import org.keycloak.credential.CredentialInput;
import org.keycloak.credential.CredentialInputValidator;
import org.keycloak.credential.UserCredentialStore;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserModel;
import org.keycloak.models.credential.PasswordCredentialModel;
import org.keycloak.storage.UserStorageProvider;
import org.keycloak.storage.adapter.AbstractUserAdapter;
import org.keycloak.storage.user.UserLookupProvider;

@Slf4j
public class RemoteUserStorageProvider implements UserStorageProvider, UserLookupProvider, CredentialInputValidator {

    private KeycloakSession keycloakSession;
    private ComponentModel componentModel;
    private UsersApiService usersApiService;

    public RemoteUserStorageProvider(KeycloakSession keycloakSession, ComponentModel componentModel,
            UsersApiService usersApiService) {
        this.keycloakSession = keycloakSession;
        this.componentModel = componentModel;
        this.usersApiService = usersApiService;
    }

    @Override
    public void close() {

    }

    @Override
    public UserModel getUserById(String s, RealmModel realmModel) {
        return null;
    }

    @Override
    public UserModel getUserByUsername(String username, RealmModel realmModel) {
        UserModel returnedValue = null;
        User userDetails = usersApiService.getUserDetails(username);
        return userDetails != null ? createUserModel(username, realmModel) : null;
        //        if (Objects.nonNull(userDetails)) {
        //            returnedValue = createUserModel(username, realmModel);
        //        }
        //        return returnedValue;
    }

    private UserModel createUserModel(String username, RealmModel realmModel) {
        return new AbstractUserAdapter(keycloakSession, realmModel, componentModel) {
            @Override
            public String getUsername() {
                return username;
            }
        };
    }

    @Override
    public boolean isValid(RealmModel realmModel, UserModel userModel, CredentialInput credentialInput) {
        VerifyPasswordResponse verifyPasswordResponse = usersApiService.verifyPassword(userModel.getUsername(),
                credentialInput.getChallengeResponse());
        return verifyPasswordResponse == null ? false : verifyPasswordResponse.isResult();
    }

    @Override
    public UserModel getUserByEmail(String s, RealmModel realmModel) {
        return null;
    }

    @Override
    public boolean supportsCredentialType(String credentialsType) {
        return PasswordCredentialModel.TYPE.equals(credentialsType);
    }

    @Override
    public boolean isConfiguredFor(RealmModel realmModel, UserModel userModel, String credentialsType) {
        return supportsCredentialType((credentialsType)) == false ? false
                : !getCredentialsStore().getStoredCredentialsByType(realmModel, userModel, credentialsType).isEmpty();

        //        if(!supportsCredentialType((credentialsType))){
        //            return false;
        //        }
        //
        //        return !getCredentialsStore().getStoredCredentialsByType(realmModel, userModel, credentialsType).isEmpty();
    }

    private UserCredentialStore getCredentialsStore() {
        return keycloakSession.userCredentialManager();
    }

}
