from common.factory.credentials_factory import ICredentialsFactory
from abc import ABC, abstractmethod
from keycloak import KeycloakAdmin
from config import Config

class IKeycloakManager(ABC):
    @abstractmethod
    def verify(self, keycloak_id: str) -> None:
        pass

class KeycloakManager(IKeycloakManager):
    def __init__(self, credentials_factory: ICredentialsFactory, config: Config):
        self.__credentials_factory = credentials_factory
        self.__keycloak_admin = KeycloakAdmin(
            server_url = config.keycloak_server_url,
            realm_name = config.keycloak_realm,
            client_id = config.keycloak_client_id,
            client_secret_key = config.keycloak_client_secret,
            grant_type = 'client_credentials'
        )

    def verify(self, keycloak_id: str) -> None:
        self.__keycloak_admin.set_user_password(
            user_id = keycloak_id,
            password = self.__credentials_factory.generate(),
            temporary = False
        )

        self.__keycloak_admin.update_user(
            user_id = keycloak_id,
            payload = {
                'emailVerified': True,
                'requiredActions': []
            }
        )
