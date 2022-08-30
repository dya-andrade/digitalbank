package br.com.digitalbank.transacao.proxy;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "conta-service")
public interface ContaProxy {

}
