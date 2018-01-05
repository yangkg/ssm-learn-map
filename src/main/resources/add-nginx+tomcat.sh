#!/bin/bash
# Description: Add Brand New Project Environment OR New Tomcat Container
# Author  : dongfeng@360buy.com
# Date  : 2012.08.16
# Date  : 2013.02.16 by wangfei
# Date for changed  : 2013.04.28 by liufapeng
#
if $1 == "" ;then
echo "Usage: $0 ***.jd.com"
exit 0
fi
cd `dirname $0`
DOMAIN=$1
#get first part of $DOMAIN
PRE_DOMAIN=${DOMAIN%.*}
function BASE_WORKS ()
{
mkdir -p /export/App/
mkdir -p /export/Shell/
mkdir -p /export/Domains/
mkdir -p /export/Logs/
mkdir -p /export/Data/
mkdir -p /export/Config/
mkdir -p /export/servers/nginx/logs/
}
#First time,creat base directory
BASE_WORKS
######################## Judging A Server is New or Old ################################
NEW_SERVER="server1"
if wc -l` == 1 || `ls -l /export/Domains/|wc -l` == 0
then
NEW_SERVER="server1"
DOWNPORT="9001"
HTTPPORT="8001"
else
HTTP_MAXPORT=`cat /export/Domains/*/*/conf/server.xml |grep "HTTP" |awk '{print $2}'|awk -F "=" '{print $2}' |sed 's/\"//g'|awk 'BEGIN{x=$1;} {for(i=1;i<=NF;i++)if($i>x)x=$i;} END{print x}'`
TOMCAT_DOWNPORT=`cat /export/Domains/*/*/conf/server.xml |grep "Server port" |awk -F = '{print $2}'|awk '{print $1}' |sed 's/\"//g'|awk 'BEGIN{x=$1;} {for(i=1;i<=NF;i++)if($i>x)x=$i;} END{print x}'`
DOWNPORT=`awk 'BEGIN{nu=('${TOMCAT_DOWNPORT}'+1);print nu}'`
HTTPPORT=`awk 'BEGIN{nu=('${HTTP_MAXPORT}'+1);print nu}'`
fi
################################## NEW PROJECT_BASE WORKS ######################################
function NEW_BASE_WORKS ()
{
mkdir -p /export/App/${DOMAIN}
mkdir -p /export/Shell/${DOMAIN}
mkdir -p /export/Domains/${DOMAIN}
mkdir -p /export/Logs/${DOMAIN}
mkdir -p /export/Data/${DOMAIN}
mkdir -p /export/Config/${DOMAIN}
mkdir -p /export/servers/nginx/logs/${DOMAIN}
cp tomcat /export/Shell/${DOMAIN}/
chown -R admin.admin /export/Shell/${DOMAIN}
chown -R admin.admin /export/Shell
chmod +x /export/Shell/${DOMAIN}/tomcat
chown -R admin.admin /export/servers/nginx/conf/domains/
chown -R admin.admin /export/Domains/
chown -R admin.admin /export/App
echo "su - admin -c /export/Domains/${DOMAIN}/${NEW_SERVER}/bin/start.sh" >>/etc/rc.local
ln -s /export/Domains/${DOMAIN}/${NEW_SERVER}/logs/catalina.out /export/App/${DOMAIN}/catalina.out
ln -s /export/Domains/${DOMAIN}/${NEW_SERVER}/bin/stop.sh /export/App/${DOMAIN}/stop.sh
ln -s /export/Domains/${DOMAIN}/${NEW_SERVER}/bin/start.sh /export/App/${DOMAIN}/start.sh
#echo "/bin/sh /export/Shell/${DOMAIN}/tomcat start" >>/export/App/${DOMAIN}/start.sh
#echo "/bin/sh /export/Shell/${DOMAIN}/tomcat stop" >>/export/App/${DOMAIN}/stop.sh
#echo "/bin/sh /export/Shell/${DOMAIN}/tomcat" >>/export/App/${DOMAIN}/restart.sh
echo "${DOMAIN} is running." > /export/App/${DOMAIN}/test.jsp
chown -R admin.admin /export/App/${DOMAIN}/*
#chmod +x /export/App/${DOMAIN}/*.sh
}
############### SET TOMCAT SERVER ##################################
function TOMCAT_ADD ()
{
TOMCAT_NUM=`ls -l /export/Domains/${DOMAIN}|grep server|wc -l`
NUMBER=`awk 'BEGIN{nu=('${TOMCAT_NUM}'+1);print nu}'`
NEW_SERVER="server${NUMBER}"
CATALINA_NUM=`cat /export/Shell/${DOMAIN}/tomcat|grep "CATALINA_BASE.="|wc -l`
NEW_CATALINA_NUM=`awk 'BEGIN{nu=('${CATALINA_NUM}'+1);print nu}'`
TOMCAT_LINE_NUM=`cat -n /export/Shell/${DOMAIN}/tomcat|grep "CATALINA_BASE.="|tail -n 1 |awk '{print $1}'`
SH_FILE="/export/Shell/${DOMAIN}/tomcat"
SH_TMP_FILE="/export/Shell/${DOMAIN}/tomcat_tmp"
EXPORT_NUM_NOW=`cat ${SH_FILE} |grep "CATALINA_BASE.="|awk -F "" '{print $21}'|tail -n 1`
EXPORT_NEW=`awk 'BEGIN{nu=('${EXPORT_NUM_NOW}'+1);print nu}'`
tar xvf server100.tar.gz >>/dev/null
mv server100 ${NEW_SERVER}
sed "${TOMCAT_LINE_NUM}aexport CATALINA_BASE${EXPORT_NEW}=\/export\/Domains\/${DOMAIN}\/${NEW_SERVER}" ${SH_FILE} >${SH_TMP_FILE}
mv -f ${SH_TMP_FILE} ${SH_FILE}
sed -i "/^for.*$/s//& \$CATALINA\_BASE${NEW_CATALINA_NUM}/g" ${SH_FILE}
sed -i "s#www.360buy.com/server1#${DOMAIN}\/server${NUMBER}#g" ${SH_FILE}
sed -i "s#www.360buy.com/server1#${DOMAIN}\/${NEW_SERVER}#g" ${NEW_SERVER}/bin/*
sed -i "s#www.360buy.com\\\/server1#${DOMAIN}\\\/${NEW_SERVER}#g" ${NEW_SERVER}/bin/*
sed -i "s#8100#${DOWNPORT}#g" ${NEW_SERVER}/conf/server.xml
sed -i "s#8001#${HTTPPORT}#g" ${NEW_SERVER}/conf/server.xml
sed -i "s#App#App/${DOMAIN}#g" ${NEW_SERVER}/conf/Catalina/localhost/ROOT.xml
mv ${NEW_SERVER} /export/Domains/${DOMAIN}/
chown -R admin.admin /export/Shell/${DOMAIN}
chown -R admin.admin /export/servers/nginx/conf/domains/
chown -R admin.admin /export/Domains/
chown -R admin.admin /export/App
chown -R admin.admin /export/Shell
chmod +x /export/Shell/${DOMAIN}/tomcat
echo "Tomcat Config Complete...."
}
function TOMCAT_NEW ()
{
ADD_NEW_SERVER="server1"
CATALINA_NUM=`cat /export/Shell/${DOMAIN}/tomcat|grep "CATALINA_BASE.="|wc -l`
NEW_CATALINA_NUM=`awk 'BEGIN{nu=('${CATALINA_NUM}'+1);print nu}'`
TOMCAT_LINE_NUM=`cat -n /export/Shell/${DOMAIN}/tomcat|grep "CATALINA_BASE.="|tail -n 1 |awk '{print $1}'`
SH_FILE="/export/Shell/${DOMAIN}/tomcat"
tar xvf server100.tar.gz >>/dev/null
mv server100 ${ADD_NEW_SERVER}
sed -i "s#www.360buy.com\/server1#${DOMAIN}\/${ADD_NEW_SERVER}#g" ${SH_FILE}
sed -i "s#www.360buy.com\/server1#${DOMAIN}\/${ADD_NEW_SERVER}#g" ${ADD_NEW_SERVER}/bin/*
sed -i "s#www.360buy.com\\\/server1#${DOMAIN}\\\/${ADD_NEW_SERVER}#g" ${ADD_NEW_SERVER}/bin/*
sed -i "s#8100#${DOWNPORT}#g" ${ADD_NEW_SERVER}/conf/server.xml
sed -i "s#8001#${HTTPPORT}#g" ${ADD_NEW_SERVER}/conf/server.xml
sed -i "s#App#App/${DOMAIN}#g" ${ADD_NEW_SERVER}/conf/Catalina/localhost/ROOT.xml
mv ${ADD_NEW_SERVER} /export/Domains/${DOMAIN}/
chown -R admin.admin /export/Shell
chmod +x /export/Shell/${DOMAIN}/tomcat
echo "Tomcat Config Complete...."
}
################### SET NGINX SERVER ################################
function NGINX_NEW ()
{
PROXY_NAME=${PRE_DOMAIN}
NEW_NGINX_FILE="/export/servers/nginx/conf/domains/${DOMAIN}"
cp xxx.jd.com ${NEW_NGINX_FILE}
sed -i "s#xxx.jd.com#${DOMAIN}#g" ${NEW_NGINX_FILE}
sed -i "s#tomcat_xxx#tomcat_${PROXY_NAME}#g" ${NEW_NGINX_FILE}
sed -i "s#8001#${HTTPPORT}#g" ${NEW_NGINX_FILE}
sed -i "/server_name/s/local/local\ ${PRE_DOMAIN}\.com/g" ${NEW_NGINX_FILE}
echo "Nginx Config Complete...."
}
function NGINX_ADD ()
{
NGINX_TMP_FILE="/export/servers/nginx/conf/domains/${DOMAIN}_tmp"
NGINX_FILE="/export/servers/nginx/conf/domains/${DOMAIN}"
LINE_NUM=`cat -n /export/servers/nginx/conf/domains/${DOMAIN} |grep "server 127"|tail -n 1|awk '{print $1}'`
sed "${LINE_NUM}a\\\t\tserver\ 127.0.0.1\:${HTTPPORT}\ \ weight\=10\ max_fails\=2\ fail_timeout\=30s\;" ${NGINX_FILE} >${NGINX_TMP_FILE}
mv -f ${NGINX_TMP_FILE} ${NGINX_FILE}
echo "Nginx Config Complete...."
}

function NGINX_RELOAD ()
{
/export/servers/nginx/sbin/nginx -s reload
echo "Nginx reload complete..."
}
################## FOR REAL ###############################
if [ -d /export/Domains/${DOMAIN} ];then
echo -n "Project $1 exists,Would you like to create a single Tomcat Container ?(yes/no)"
read YES
case $YES in
y|yes|Y|YES)
TOMCAT_ADD
NGINX_ADD
NGINX_RELOAD
esac
case $YES in
n|no|N|NO)
exit
esac
else
mkdir /export/Domains/${DOMAIN}
NEW_BASE_WORKS
TOMCAT_NEW
NGINX_NEW
NGINX_RELOAD
fi
######################### END ##########################
#####change owner######
chown -R admin.admin /export/{Domains,Logs,Config,Data}