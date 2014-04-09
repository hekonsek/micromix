package micromix.conflet.restgateway

import org.springframework.beans.factory.annotation.Value
import FixedTokenAuthGatewayInterceptor._
import micromix.services.restgateway.api.{GatewayRequest, GatewayInterceptor}

case class FixedTokenAuthGatewayInterceptor() extends GatewayInterceptor {

  // Members

  @Value("${micromix.conflet.restgateway.token.expected:secretMicroMixToken}")
  private[restgateway] var expectedToken: String = _

  // Constructors

  def this(token: String) = {
    this()
    expectedToken = token
  }

  // Overridden

  override def intercept(gatewayRequest: GatewayRequest): Boolean =
    gatewayRequest.headers.get(tokenHeader) == expectedToken

}

object FixedTokenAuthGatewayInterceptor {

  val tokenHeader = "micromix.conflet.restgateway.token"

  val defaultToken = "secretMicroMixToken"

}