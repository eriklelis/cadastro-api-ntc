package com.hoteis.api_cadastro.clients.keycloak;

import com.hoteis.api_cadastro.clients.keycloak.request_dto.AddRoleByClientRequest;
import com.hoteis.api_cadastro.clients.keycloak.request_dto.CreateClientRequest;
import com.hoteis.api_cadastro.clients.keycloak.request_dto.CreateRolesRequest;
import com.hoteis.api_cadastro.clients.keycloak.request_dto.CreateUserRequest;
import com.hoteis.api_cadastro.clients.keycloak.request_dto.GroupsRequest;
import com.hoteis.api_cadastro.clients.keycloak.response.GroupMemberResponse;
import com.hoteis.api_cadastro.clients.keycloak.response.GroupResponse;
import com.hoteis.api_cadastro.clients.keycloak.response.RoleResponse;
import com.hoteis.api_cadastro.clients.keycloak.response.SecretResponse;
import com.hoteis.api_cadastro.clients.keycloak.response.UserResponse;
import com.hoteis.api_cadastro.configuration.feing.FeingInterceptorConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "keycloakRequests", url = "KEYCLOAK_ADM_API", configuration = FeingInterceptorConfig.class)
public interface KeycloakService {
    @GetMapping("/clients/{client_id}/client-secret")
    ResponseEntity<SecretResponse> getSecretByClientId(@PathVariable("client_id") String clientId);

    @PostMapping("/clients")
    ResponseEntity<SecretResponse> createClient(@RequestBody CreateClientRequest createClientRequest);

    @GetMapping("/groups")
    ResponseEntity<List<GroupResponse>> getAllGroups();

    @GetMapping("/groups?search={group_name}")
    ResponseEntity<List<GroupResponse>> getGroupByName(@PathVariable("group_name") String groupName);

    @GetMapping("/groups/{group_id}/members?max=1000")
    ResponseEntity<List<GroupMemberResponse>> getGroupMember(@PathVariable("group_id") String groupId);

    @GetMapping("/clients/{client_id}/roles")
    ResponseEntity<List<RoleResponse>> getAllRoles(@PathVariable("client_id") String clientId);

    @PostMapping("/clients/{client_id}/roles")
    ResponseEntity<?> createRoles(@PathVariable("client_id") String clientId,
                                  @RequestBody CreateRolesRequest createRolesByClientRequest);

    @PostMapping("/users")
    ResponseEntity<?> createUser(@RequestBody CreateUserRequest createClientRequest);

    @PostMapping("/groups")
    ResponseEntity<?> createGroup(@RequestBody GroupsRequest createClientRequest);

    @PostMapping("/users/{user_id}/role-mappings/clients/{client_id}")
    ResponseEntity<?> addRoleByClientInUser(@PathVariable("client_id") String clientId,
                                            @PathVariable("user_id") String userId, @RequestBody List<AddRoleByClientRequest> createClientRequest);

    @GetMapping("/users?username={username}")
    ResponseEntity<List<UserResponse>> getUserByUsername(@PathVariable("username") String username);

    @GetMapping("/users?email={email}")
    ResponseEntity<List<UserResponse>> getUserByEmail(@PathVariable("email") String email);


}
