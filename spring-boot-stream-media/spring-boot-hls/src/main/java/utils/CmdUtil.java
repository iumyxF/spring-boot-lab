package utils;

import cn.hutool.core.text.StrBuilder;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.CharUtil;
import cn.hutool.core.util.StrUtil;
import org.apache.commons.lang3.SystemUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author fzy
 * @description: cmd工具类
 * @date 2023/5/24 14:18
 */
public class CmdUtil {

    /**
     * 执行命令<br>
     * 命令带参数时参数可作为其中一个参数，也可以将命令和参数组合为一个字符串传入
     *
     * @param cmds 命令
     * @return {@link Process}
     */
    public static ProcessBuilder exec(String... cmds) {
        ProcessBuilder builder = new ProcessBuilder(handleCmds(cmds));
        if (SystemUtils.IS_OS_WINDOWS) {
            builder.redirectOutput(new File("NUL"));
        } else {
            builder.redirectOutput(new File("/dev/null"));
        }
        if (SystemUtils.IS_OS_WINDOWS) {
            builder.redirectError(new File("NUL"));
        } else {
            builder.redirectError(new File("/dev/null"));
        }
        return builder;
    }

    /**
     * 处理命令，多行命令原样返回，单行命令拆分处理
     *
     * @param cmds 命令
     * @return 处理后的命令
     */
    private static String[] handleCmds(String... cmds) {
        if (ArrayUtil.isEmpty(cmds)) {
            throw new NullPointerException("Command is empty !");
        }

        // 单条命令的情况
        if (1 == cmds.length) {
            final String cmd = cmds[0];
            if (StrUtil.isBlank(cmd)) {
                throw new NullPointerException("Command is blank !");
            }
            cmds = cmdSplit(cmd);
        }
        return cmds;
    }

    /**
     * 命令分割，使用空格分割，考虑双引号和单引号的情况
     *
     * @param cmd 命令，如 git commit -m 'test commit'
     * @return 分割后的命令
     */
    private static String[] cmdSplit(String cmd) {
        final List<String> cmds = new ArrayList<>();

        final int length = cmd.length();
        final Stack<Character> stack = new Stack<>();
        boolean inWrap = false;
        final StrBuilder cache = StrUtil.strBuilder();

        char c;
        for (int i = 0; i < length; i++) {
            c = cmd.charAt(i);
            switch (c) {
                case CharUtil.SINGLE_QUOTE:
                case CharUtil.DOUBLE_QUOTES:
                    if (inWrap) {
                        if (c == stack.peek()) {
                            //结束包装
                            stack.pop();
                            inWrap = false;
                        }
                        cache.append(c);
                    } else {
                        stack.push(c);
                        cache.append(c);
                        inWrap = true;
                    }
                    break;
                case CharUtil.SPACE:
                    if (inWrap) {
                        // 处于包装内
                        cache.append(c);
                    } else {
                        cmds.add(cache.toString());
                        cache.reset();
                    }
                    break;
                default:
                    cache.append(c);
                    break;
            }
        }

        if (cache.hasContent()) {
            cmds.add(cache.toString());
        }

        return cmds.toArray(new String[0]);
    }

    /**
     * 最简单的执行cmd方式
     *
     * @param cmd
     * @return
     * @throws IOException
     */
    public static Process simpleExec(String cmd) throws IOException {
        // 在windows环境下，使用Runtime类的exec方法执行cmd命令
        // 返回Process对象
        return Runtime.getRuntime().exec(cmd);
    }
}
