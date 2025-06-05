from common.factory.user_factory import IUserFactory
from common.manager.keycloak_manager import IKeycloakManager
from config import Config
import requests
import logging
import urllib

class UserGenerator:
    def __init__(self, keycloak_manager: IKeycloakManager, user_factory: IUserFactory, config: Config):
        self.__keycloak_manager = keycloak_manager
        self.__user_factory = user_factory
        self.__config = config
    
    def __create(self) -> dict:
        url = urllib.parse.urljoin(self.__config.api_base_url + '/', 'api/v1/users')
        payload = self.__user_factory.generate()
        response = requests.post(url, json = payload)
        response.raise_for_status()
        return response.json()

    def generate(self, num_users: int = 10) -> None:
        for _ in range(num_users):
            try:
                keycloak_id = self.__create().get('keycloakId')
                self.__keycloak_manager.verify(keycloak_id)
            except Exception as ex:
                logging.error(ex)
