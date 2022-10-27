package net.nergi.eternitywrapper.model

import java.net.InetAddress

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

  override protected def serialiseContent: String = num.toString
}

case class Episode(num: Int) extends Option {
  override def name: String = "episode"

  override protected def serialiseContent: String = num.toString
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

  override protected def serialiseContent: String = level.toString
}

case object SoloNet extends Flag {
  override def name: String = "solo-net"
}

case class SpecHit(num: BigInt) extends Option {
  override def name: String = "spechit"

  override protected def serialiseContent: String = num.toString
}

case class Turbo(factor: Int) extends Option {
  override def name: String = "turbo"

  override protected def serialiseContent: String = factor.toString
}

case class Warp(where: Warp.WarpKind) extends Option {
  override def name: String = "warp"

  override protected def serialiseContent: String = where match {
    case Warp.WarpEM(e, m) => s"$e $m"
    case Warp.WarpM(m)     => m.toString
    case Warp.WarpNS(n)    => n
  }
}

object Warp {
  sealed trait WarpKind

  case class WarpEM(episode: Int, mission: Int) extends WarpKind
  case class WarpM(map: Int) extends WarpKind
  case class WarpNS(name: String) extends WarpKind
}

// Miscellaneous options.
case class Affinity(mask: Int) extends Option {
  override def name: String = "mask"

  override protected def serialiseContent: String = mask.toString
}

case object Blockmap extends Flag {
  override def name: String = "blockmap"
}

case object GrabMouse extends Flag {
  override def name: String = "grabmouse"
}

case object NoGrabMouse extends Flag {
  override def name: String = "nograbmouse"
}

case class NumParticles(count: Int) extends Option {
  override def name: String = "numparticles"

  override protected def serialiseContent: String = count.toString
}

case class Speed(percent: Int) extends Option {
  override def name: String = "speed"

  override protected def serialiseContent: String = percent.toString
}

case object StatCopy extends Flag {
  override def name: String = "statcopy"
}

// Multiplayer options.
case object AltDeath extends Flag {
  override def name: String = "altdeath"
}

case object AustinVirtualGaming extends Flag {
  override def name: String = "avg"
}

case object Deathmatch extends Flag {
  override def name: String = "deathmatch"
}

case class DmFlags(flags: Int) extends Option {
  override def name: String = "dmflags"

  override protected def serialiseContent: String = flags.toString
}

case object ExtraTic extends Flag {
  override def name: String = "extratic"
}

case class Frags(limit: Int) extends Option {
  override def name: String = "frags"

  override protected def serialiseContent: String = limit.toString
}

case class Net(player: Int, ips: InetAddress*) extends Option {
  override def name: String = "net"

  override protected def serialiseContent: String =
    s"$player " + ips.map(_.getHostAddress()).mkString(" ")
}

case class Port(port: Int) extends Option {
  override def name: String = "port"

  override protected def serialiseContent: String = port.toString
}

case class Timer(mins: Int) extends Option {
  override def name: String = "timer"

  override protected def serialiseContent: String = mins.toString
}

case object TriDeath extends Flag {
  override def name: String = "trideath"
}

// Savegame options.
case class LoadGame(slot: Int) extends Option {
  override def name: String = "loadgame"

  override protected def serialiseContent: String = slot.toString
}

case class Save(savepath: String) extends Option {
  override def name: String = "save"

  override protected def serialiseContent: String = wrap(savepath)
}

// Video options.
case object EightIn32 extends Flag {
  override def name: String = "8in32"
}

case object DirectX extends Flag {
  override def name: String = "directx"
}

case object Frame extends Flag {
  override def name: String = "frame"
}

case object Fullscreen extends Flag {
  override def name: String = "fullscreen"
}

case object GDI extends Flag {
  override def name: String = "gdi"
}

case class Geom(width: Int, height: Int, flags: Geom.GeomFlag*) extends Option {
  override def name: String = "geom"

  override protected def serialiseContent: String = s"${width}x${height}" + flags.mkString
}

case object Geom {
  sealed abstract class GeomFlag(val repr: Char) {
    override def toString(): String = s"$repr"
  }

  case object Windowed extends GeomFlag('w')
  case object Fullscreen extends GeomFlag('f')
  case object DesktopFullscreen extends GeomFlag('d')
  case object PageFlipUpdates extends GeomFlag('v')
  case object SoftwareSurface extends GeomFlag('s')
  case object HardwareSurface extends GeomFlag('h')
  case object Frameless extends GeomFlag('n')
}

case object Hardware extends Flag {
  override def name: String = "hardware"
}

case object NoBlit extends Flag {
  override def name: String = "noblit"
}

case object NoDraw extends Flag {
  override def name: String = "nodraw"
}

case object NoFrame extends Flag {
  override def name: String = "noframe"
}

case object NoFullscreen extends Flag {
  override def name: String = "nofullscreen"
}

case object NoVSync extends Flag {
  override def name: String = "novsync"
}

case object Software extends Flag {
  override def name: String = "software"
}

case class VHeight(height: Int) extends Option {
  override def name: String = "vheight"

  override protected def serialiseContent: String = height.toString
}

case object VSync extends Flag {
  override def name: String = "vsync"
}

case class VWidth(width: Int) extends Option {
  override def name: String = "vwidth"

  override protected def serialiseContent: String = width.toString
}

case object Window extends Flag {
  override def name: String = "window"
}

// WAD and file options.
case class Base(basepath: String) extends Option {
  override def name: String = "base"

  override protected def serialiseContent: String = wrap(basepath)
}

case class Config(configpath: String) extends Option {
  override def name: String = "config"

  override protected def serialiseContent: String = wrap(configpath)
}

case class Disk(diskpath: String) extends Option {
  override def name: String = "disk"

  override protected def serialiseContent: String = wrap(diskpath)
}

case class DataFile(files: String*) extends Option {
  override def name: String = "file"

  override protected def serialiseContent: String = files.map(wrap).mkString(" ")
}

case class Game(gamename: String) extends Option {
  override def name: String = "game"

  override protected def serialiseContent: String = gamename
}

case class GFS(scriptpath: String) extends Option {
  override def name: String = "gfs"

  override protected def serialiseContent: String = wrap(scriptpath)
}

case class IWAD(wadpath: String) extends Option {
  override def name: String = "iwad"

  override protected def serialiseContent: String = wrap(wadpath)
}

case object NoLoad extends Flag {
  override def name: String = "noload"
}

case object NoWADHacks extends Flag {
  override def name: String = "nowadhacks"
}

case object ShowHashes extends Flag {
  override def name: String = "showhashes"
}

case class User(userpath: String) extends Option {
  override def name: String = "user"

  override protected def serialiseContent: String = wrap(userpath)
}

// Demo options.
case class DemoLog(logpath: String) extends Option {
  override def name: String = "demolog"

  override protected def serialiseContent: String = wrap(logpath)
}

case class Donut(height: Long = 0xf1000000, pic: Int = 7) extends Option {
  override def name: String = "donut"

  override protected def serialiseContent: String = s"${height.toHexString} $pic"
}

case class FastDemo(demopath: String) extends Option {
  override def name: String = "fastdemo"

  override protected def serialiseContent: String = wrap(demopath)
}

case object LongTics extends Flag {
  override def name: String = "longtics"
}

case class MaxDemo(buflimit: Int) extends Option {
  override def name: String = "maxdemo"

  override protected def serialiseContent: String = buflimit.toString
}

case class Play(demopath: String) extends Option {
  override def name: String = "play"

  override protected def serialiseContent: String = wrap(demopath)
}

case class Record(demopath: String) extends Option {
  override def name: String = "record"

  override protected def serialiseContent: String = wrap(demopath)
}

case class RecordFromTo(demo1: String, demo2: String) extends Option {
  override def name: String = "recordfromto"

  override protected def serialiseContent: String = s"${wrap(demo1)} ${wrap(demo2)}"
}

case object RejectPadWithFF extends Flag {
  override def name: String = "reject_pad_with_ff"
}

case class TimeDemo(demopath: String) extends Option {
  override def name: String = "timedemo"

  override protected def serialiseContent: String = wrap(demopath)
}

case object Vanilla extends Flag {
  override def name: String = "vanilla"
}
