package jsi.mentorship.ws.request;

import java.io.Serializable;

import jsi.mentorship.models.concretes.User;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class JwtResponse implements Serializable
{

    private final String accessToken;
    private final User user;


}