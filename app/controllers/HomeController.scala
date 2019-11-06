package controllers

import de.htwg.se.skyjo.Skyjo
import javax.inject._
import play.api.mvc._

@Singleton
class HomeController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  val gameController = Skyjo.controller

  def about() = Action {
    Ok(views.html.index())
  }

  def skyjo = Action {
    Ok(views.html.game(gameController))
  }

  def playerList = Action {
    Ok(gameController.players.toString())
  }

  def addPlayer(player: String) = Action {
    gameController.createPlayer(player)
    Ok(views.html.game(gameController))
  }

  def uncover(posX: Int, posY: Int, player: Int) = Action {
    gameController.setCursor(posX, posY, gameController.players(player))
    gameController.uncoverCard(gameController.players(player))
    Ok(views.html.game(gameController))
  }

}
