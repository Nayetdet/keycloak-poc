from common.factory.user_factory import UserFactory
from common.factory.credentials_factory import CredentialsFactory
from common.manager.keycloak_manager import KeycloakManager
from generator.module.user_generator import UserGenerator
from faker import Faker
from config import Config

def load_config() -> Config:
    return Config(
        api_base_url = 'http://localhost:8081',
        keycloak_server_url = 'http://localhost:8080',
        keycloak_realm = 'keycloak-poc',
        keycloak_client_id = 'keycloak-poc',
        keycloak_client_secret = 'bPaMJzSAYoqM2itDK3zb1xFIk8cp5fHi'
    )

def main():
    faker = Faker()
    user_factory = UserFactory(faker)
    credentials_factory = CredentialsFactory(faker)

    config = load_config()
    keycloak_manager = KeycloakManager(credentials_factory, config)

    user_generator = UserGenerator(keycloak_manager, user_factory, config)
    user_generator.generate()

if __name__ == '__main__':
    main()
