public static void createScoreboard(Player player, String header, List<String> scores) {
        net.minecraft.server.v1_8_R3.Scoreboard sb = new net.minecraft.server.v1_8_R3.Scoreboard();
        ScoreboardObjective obj = sb.registerObjective("dummy", IScoreboardCriteria.b);
        obj.setDisplayName(header);
        PacketPlayOutScoreboardObjective createPacket = new PacketPlayOutScoreboardObjective(obj, 0);
        PacketPlayOutScoreboardDisplayObjective display = new PacketPlayOutScoreboardDisplayObjective(1, obj);
        PacketPlayOutScoreboardObjective removePacket = new PacketPlayOutScoreboardObjective(obj, 1);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(removePacket);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(createPacket);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(display);
        int i = 0;
        for(String cscore : scores) {
            ScoreboardScore score = new ScoreboardScore(sb,obj, cscore);
            score.setScore(scores.size()-i);
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(new PacketPlayOutScoreboardScore(score));
            i++;
        }
    }
