(window.webpackJsonp=window.webpackJsonp||[]).push([[22],{836:function(e,a,t){"use strict";t.r(a);var n=t(45),s=t.n(n),r=t(82),o=t(17),l=t(5),c=t(25),i=t(26),d=t(214),u=t(38),m=t(37),w=t(0),p=t.n(w),h=t(550),g=t(198),b=t(321),f=t(535),E=t(35),P=t(208),v=t(121),O=t(123),j=t(13),S=t(56),C=t(110),y=t(211),k=t(209),x=t.n(k),N=t(553),B=t(568),F=O.object().shape({oldPassword:N.w,newpassword:N.t,confirmpassword:O.string().when("newpassword",{is:function(e){return!!(e&&e.length>0)},then:O.string().oneOf([O.ref("newpassword")],B.v.passwordMustMatch)})}),z=function(e){Object(u.a)(t,e);var a=Object(m.a)(t);function t(e){var n;return Object(c.a)(this,t),(n=a.call(this,e)).toggleShow=function(e){if(n.setState(function(a){return{hidden:Object(l.a)(Object(l.a)({},a.hidden),{},Object(o.a)({},e,!a.hidden[e]))}}),!0===n.state.hidden[e]){n.setState(function(a){return{names:Object(l.a)(Object(l.a)({},a.names),{},Object(o.a)({},e,"eye slash"))}})}else{n.setState(function(a){return{names:Object(l.a)(Object(l.a)({},a.names),{},Object(o.a)({},e,"eye"))}})}},n.state={hidden:{0:!0,1:!0,2:!0},names:{0:"eye",1:"eye",2:"eye"}},n.handlePasswordChange=n.handlePasswordChange.bind(Object(d.a)(n)),n.toggleShow=n.toggleShow.bind(Object(d.a)(n)),n}return Object(i.a)(t,[{key:"handlePasswordChange",value:function(e){this.setState(Object(o.a)({},e.target.name,e.target.value))}},{key:"render",value:function(){var e=this;return p.a.createElement(v.c,{initialValues:{oldPassword:"",newpassword:"",confirmpassword:"",ui:{}},validationSchema:F,onSubmit:function(){var a=Object(r.a)(s.a.mark(function a(t,n){var r;return s.a.wrap(function(a){for(;;)switch(a.prev=a.next){case 0:return n.setSubmitting(!0),(r=new FormData).append("password",t.newpassword),r.append("oldPassword",t.oldPassword),a.prev=4,a.next=7,e.props.postPassword(r);case 7:window.scrollTo(0,0),e.props.success&&e.props.success.success&&Object(j.x)("Your password has been successfully changed."),n.setSubmitting(!1),a.next=16;break;case 12:a.prev=12,a.t0=a.catch(4),setTimeout(function(){n.setSubmitting(!1)},3e3),window.scrollTo(0,0);case 16:case"end":return a.stop()}},a,null,[[4,12]])}));return function(e,t){return a.apply(this,arguments)}}()},function(a){var t=a.values,n=a.errors,s=a.handleSubmit,r=a.handleChange,o=a.handleBlur,l=a.isSubmitting;return p.a.createElement(h.a,{onSubmit:s,size:"large",loading:l,className:"adminLogin-form"},p.a.createElement(x.a,null,p.a.createElement("title",null,"Change Password")),p.a.createElement("div",null,p.a.createElement(g.a,{textAlign:"center",style:{height:"80vh"},verticalAlign:"middle"},p.a.createElement(g.a.Column,{style:{maxWidth:450}},p.a.createElement(b.a,{as:"h2",color:"teal",textAlign:"center"},"Change Password"),p.a.createElement(h.a,{size:"large",className:"adminLogin-form"},e.props.errors&&p.a.createElement(y.b,{message:e.props.errors.message}),p.a.createElement(f.a,{stacked:!0,className:"change-password-segment"},p.a.createElement(h.a.Field,{error:n.oldPassword},p.a.createElement("label",null,"Old Password"),p.a.createElement(h.a.Input,{icon:p.a.createElement(E.a,{name:e.state.names[0],link:!0,onClick:function(){return e.toggleShow(0)}}),iconPosition:"right",type:e.state.hidden[0]?"password":"text",name:"oldPassword",value:t.oldPassword,onChange:r,onBlur:o,placeholder:"Old Password"}),n.oldPassword&&p.a.createElement("span",{className:"tableError"},n.oldPassword)),p.a.createElement(h.a.Field,{error:n.newpassword},p.a.createElement("label",null,"New Password"),p.a.createElement(h.a.Input,{icon:p.a.createElement(E.a,{name:e.state.names[1],link:!0,onClick:function(){return e.toggleShow(1)}}),iconPosition:"right",type:e.state.hidden[1]?"password":"text",name:"newpassword",value:t.newpassword,onChange:r,onBlur:o,placeholder:"New Password"}),n.newpassword&&p.a.createElement("span",{className:"tableError english-div"},n.newpassword)),p.a.createElement(h.a.Field,{error:n.confirmpassword},p.a.createElement("label",null,"Confirm New Password"),p.a.createElement(h.a.Input,{icon:p.a.createElement(E.a,{name:e.state.names[2],link:!0,onClick:function(){return e.toggleShow(2)}}),iconPosition:"right",onBlur:o,type:e.state.hidden[2]?"password":"text",name:"confirmpassword",value:t.confirmpassword,onChange:r,placeholder:"Confirm Password"}),n.confirmpassword&&p.a.createElement("span",{className:"tableError"},n.confirmpassword)),p.a.createElement(P.a,{fluid:!0,size:"large",type:"submit"},"Submit")))))))})}}]),t}(w.Component),A={postPassword:C.f};a.default=Object(S.b)(function(e){return{loading:e.root.ui.loading,errors:e.root.ui.errors,success:e.root.formData.success}},A)(z)}}]);
//# sourceMappingURL=22.2e08c8ff.chunk.js.map