class Config:
    def __init__(
        self,
        api_base_url: str,
        keycloak_server_url: str,
        keycloak_realm: str,
        keycloak_client_id: str,
        keycloak_client_secret: str
    ):
        self.api_base_url = api_base_url
        self.keycloak_server_url = keycloak_server_url
        self.keycloak_realm = keycloak_realm
        self.keycloak_client_id = keycloak_client_id
        self.keycloak_client_secret = keycloak_client_secret
