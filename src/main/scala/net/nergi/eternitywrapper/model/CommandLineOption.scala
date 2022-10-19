package net.nergi.eternitywrapper.model

// More info found at https://eternity.youfailit.net/wiki/List_of_command_line_parameters

/** Represents a single command line argument passed into the program. */
sealed trait CommandLineOption {
  def name: String

  def serialise: String
}

/** Represents a binary toggle for the program. */
sealed trait Flag extends CommandLineOption {
  override def serialise: String = "-" + name
}

/** Represents an option with textual input. */
sealed trait Option extends CommandLineOption {
  override def serialise: String = s"-$name $serialiseContent"

  protected def serialiseContent: String

  protected def wrap(str: String): String = s"\"$str\""
}

// Console options.
case class Exec(fname: String) extends Option {
  override def name: String = "exec"

  override protected def serialiseContent: String = wrap(fname)
}

case object NoCSCLoad extends Flag {
  override def name: String = "nocscload"
}

// Debugging options.
case object DefParm extends Flag {
  override def name: String = "devparm"
}

// DeHackEd / BEX options.
// Note that -bex is just an alias for -deh.
case class Deh(fnames: List[String]) extends Option {
  override def name: String = "deh"

  override protected def serialiseContent: String = fnames.map(wrap).mkString(" ")
}

// Note that -bexout is just an alias for -dehout.
case class DehOut(fname: String) extends Option {
  override def name: String = "dehout"

  override protected def serialiseContent: String = wrap(fname)
}

// EDF options.
case class EDF(fname: String) extends Option {
  override def name: String = "edf"

  override protected def serialiseContent: String = wrap(fname)
}

case object EDFEnables extends Flag {
  override def name: String = "edfenables"
}

case object EDFOut extends Flag {
  override def name: String = "edfout"
}

case object EDFShowWarnings extends Flag {
  override def name: String = "edf-show-warnings"
}

// Gameplay options.
case object Dog extends Flag {
  override def name: String = "dog"
}

case class Dogs(num: Int) extends Option {
  override def name: String = "dogs"

  override protected def serialiseContent: String = num.toString()
}

case class Episode(num: Int) extends Option {
  override def name: String = "episode"

  override protected def serialiseContent: String = num.toString()
}

case object Fast extends Flag {
  override def name: String = "fast"
}

case object NoMonsters extends Flag {
  override def name: String = "nomonsters"
}

case object Respawn extends Flag {
  override def name: String = "respawn"
}

case class Skill(level: Int) extends Option {
  override def name: String = "skill"

  override protected def serialiseContent: String = level.toString()
}

case object SoloNet extends Flag {
  override def name: String = "solo-net"
}

case class SpecHit(num: Int) extends Option {
  override def name: String = "spechit"

  override protected def serialiseContent: String = num.toString()
}

case class Turbo(factor: Int) extends Option {
  override def name: String = "turbo"

  override protected def serialiseContent: String = factor.toString()
}

case class Warp(where: Warp.WarpKind) extends Option {
  override def name: String = "warp"

  override protected def serialiseContent: String = where match {
    case Warp.WarpEM(e, m) => s"$e $m"
    case Warp.WarpM(m)     => m.toString()
    case Warp.WarpNS(n)    => n
  }
}

object Warp {
  sealed trait WarpKind

  case class WarpEM(episode: Int, mission: Int) extends WarpKind
  case class WarpM(map: Int) extends WarpKind
  case class WarpNS(name: String) extends WarpKind
}

// TODO: Fill in the rest.
