package org.example.plugin.pluginsample2;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.World;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.units.qual.C;

public final class Plugin_sample2 extends JavaPlugin implements Listener {

  private Integer count = 0;

  private int count2;
  // commitとpush用の変更

    @Override
  public void onEnable() {
    Bukkit.getPluginManager().registerEvents(this, this);
  }

  /**
   * プレイヤーがスニークを開始/終了する際に起動されるイベントハンドラ。
   *
   * @param e イベント
   */
  @EventHandler
  public void onPlayerToggleSneak(PlayerToggleSneakEvent e) throws IOException {
    // イベント発生時のプレイヤーやワールドなどの情報を変数に持つ。
    Player player = e.getPlayer();
    World world = player.getWorld();

    String intString = Integer.toString(count);
    BigInteger val = new BigInteger(intString);

    List<Color> colorList = List.of(Color.RED, Color.BLUE, Color.WHITE, Color.BLACK);
    if (val.isProbablePrime(1)) {
      for (Color color : colorList) {
        // 花火オブジェクトをプレイヤーのロケーション地点に対して出現させる。
        Firework firework = world.spawn(player.getLocation(), Firework.class);

        // 花火オブジェクトが持つメタ情報を取得。
        FireworkMeta fireworkMeta = firework.getFireworkMeta();

        // メタ情報に対して設定を追加したり、値の上書きを行う。
        // 今回は青色で星型の花火を打ち上げる。
        fireworkMeta.addEffect(
            FireworkEffect.builder()
                .withColor(Color.RED)
                .withColor(Color.BLUE)
                .with(Type.BALL_LARGE)
                .withFlicker()
                .build());
        fireworkMeta.setPower((5 - 3) * 2 / 4);

        // 追加した情報で再設定する。
        firework.setFireworkMeta(fireworkMeta);
      }

      Path path = Path.of("fireworks.txt");
      Files.writeString(path, "たーまやー", StandardOpenOption.APPEND);
      player.sendMessage(Files.readString(path));

    }
    count++;
  }

//    @EventHandler
//    public void onPlayerBedEnter(PlayerBedEnterEvent e) {
//        Player player = e.getPlayer();
//        ItemStack[] itemStacks = player.getInventory().getContents();
//
////        Arrays.stream(itemStacks)
////          .filter(item -> !Objects.isNull(item) && item.getMaxStackSize() == 64 && item.getAmount() < 64)
////          .forEach(item -> item.setAmount(0));
////        player.getInventory().setContents(itemStacks);
//
//        Arrays.stream(itemStacks)
//            .filter(item -> !Objects.isNull(item) && item.getMaxStackSize() == 64 && item.getAmount() < 64)
//            .forEach(item -> item.setAmount(0));
//
//        player.getInventory().setContents(itemStacks);
//    }
}
