package controllers

import javax.inject.Inject
import play.api.mvc._

class AuthenticatedController @Inject()(
                                         cc: ControllerComponents,
                                         authenticatedAction: AuthenticatedAction
                                       ) extends AbstractController(cc) {
  def logout: Action[AnyContent] = authenticatedAction { implicit request: Request[AnyContent] =>
    Redirect("/login").withNewSession
  }
}
