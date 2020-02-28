package net.shop.service;

import net.shop.model.SocialAccount;

/**
 * Этот интерфейс служит для получения SocialAccount из любой соцсети.
 */
public interface SocialService {

    /**
     * Возвращает Url, по которому нужно сделать редирект пользователю чтобы сделать запрос с соцсети.
     * Короче, формирует запрос в соцсеть для авторизации
     * @return
     */
    String getAuthorizeUrl();

    SocialAccount getSocialAccount(String authToken);

}
