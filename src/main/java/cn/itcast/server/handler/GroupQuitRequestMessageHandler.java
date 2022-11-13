package cn.itcast.server.handler;

/*
@author YG
@create 2022/11/9   20:38
*/

import cn.itcast.message.GroupQuitRequestMessage;
import cn.itcast.message.GroupQuitResponseMessage;
import cn.itcast.server.session.Group;
import cn.itcast.server.session.GroupSessionFactory;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@ChannelHandler.Sharable
public class GroupQuitRequestMessageHandler extends SimpleChannelInboundHandler<GroupQuitRequestMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupQuitRequestMessage msg) throws Exception {
        Group group = GroupSessionFactory.getGroupSession().removeGroup(msg.getGroupName());
        if (group != null) {
            ctx.writeAndFlush(new GroupQuitResponseMessage(true, msg.getGroupName() + "群退出成功"));
        }else {
            ctx.writeAndFlush(new GroupQuitResponseMessage(false, msg.getGroupName() + "群退出失败"));
        }
    }
}
