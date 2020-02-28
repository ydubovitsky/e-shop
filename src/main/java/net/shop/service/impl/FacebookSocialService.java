package net.shop.service.impl;


import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.scope.ExtendedPermissions;
import com.restfb.scope.ScopeBuilder;
import com.restfb.types.User;
import net.shop.model.SocialAccount;
import net.shop.service.SocialService;

class FacebookSocialService implements SocialService {

    private final String idClient;
    private final String secret;
    private final String redirectUrl;

    /**
     *
     * @param serviceManager - передается в метод, для того, чтобы использовать загрузку свойст из application.properties
     */
    FacebookSocialService(ServiceManager serviceManager) {
        super();
        idClient = serviceManager.getApplicationProperty("social.facebook.idClient");
        secret = serviceManager.getApplicationProperty("social.facebook.secret");
        //! redirectUrl - на какой url фейсбук должен передать управление после авторизации
        redirectUrl = serviceManager.getApplicationProperty("app.host") + "/from-social"; //* /from-social - мапинг нашего контроллера
    }

    @Override
    public String getAuthorizeUrl() { //? Разобраться откуда это все берется
        ScopeBuilder scopeBuilder = new ScopeBuilder();
        scopeBuilder.addPermission(ExtendedPermissions.EMAIL);
        FacebookClient client = new DefaultFacebookClient(Version.VERSION_2_5);
        return client.getLoginDialogUrl(idClient, redirectUrl, scopeBuilder);
    }

    @Override
    public SocialAccount getSocialAccount(String authToken) {
        FacebookClient client = new DefaultFacebookClient(Version.VERSION_2_5);
        FacebookClient.AccessToken accessToken = client.obtainUserAccessToken(idClient, secret, redirectUrl, authToken);
        client = new DefaultFacebookClient(accessToken.getAccessToken(), Version.VERSION_2_5);
        //! User user - это класс из api Facebook, который представляет собой представление пользователя в фейсбук
        User user = client.fetchObject("me", User.class, Parameter.with("fields", "name,email,first_name,last_name"));
        //! Тут мы считываем данные из User и уже создаем объект своего класса SocialAccount
        return new SocialAccount(user.getFirstName(), user.getEmail());
    }
}